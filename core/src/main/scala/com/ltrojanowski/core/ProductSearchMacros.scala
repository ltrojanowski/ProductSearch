package com.ltrojanowski.core

import scala.annotation.tailrec
import scala.language.experimental.macros
import scala.reflect.macros.whitebox
import scala.reflect.macros.blackbox

object ProductSearchMacros {

  private def isTupleSymbol(symbol: String) = {
    val isTupleSymbolPattern = "^Tuple(\\d{1,2})$".r
    symbol match {
      case isTupleSymbolPattern(size) if Integer.valueOf(size) < 23 => true
      case _ => false
    }
  }

  def deepLeft[A1](a: (A1)): Any = macro deepLeft_impl[Tuple1[A1]]
  def deepLeft[A1, A2](a: (A1, A2)): Any = macro deepLeft_impl[Tuple2[A1, A2]]
  def deepLeft[A1, A2, A3](a: (A1, A2, A3)): Any = macro deepLeft_impl[Tuple3[A1, A2, A3]]

  def deepLeft_impl[A: c.WeakTypeTag](c: whitebox.Context)(a: c.Expr[A]): c.Expr[Any] = {
    import c.universe._

    val aTpe = weakTypeOf[A]
    c.info(c.enclosingPosition, s"a.actualType: ${a.actualType}", false)
    c.info(c.enclosingPosition, s"aTpe: ${aTpe}", false)

    def typeParams(t: Type): List[Type] = {
      val tClass = t.typeSymbol.asClass
      tClass.typeParams.map(_.asType.toType.asSeenFrom(t, tClass))
    }

    @tailrec
    def findDeepestLeftInNestedTuples(tpe: Type, depth: Int = 0): Int = {
      typeParams(tpe).headOption match {
        case Some(t) if isTupleSymbol(t.typeSymbol.name.toString) => findDeepestLeftInNestedTuples(t, depth + 1)
        case Some(t) => depth
        case None => sys.error("Tuple with no elements (´⊙ω⊙`)ʷᵗᶠ")
      }
    }
    val depth = findDeepestLeftInNestedTuples(a.actualType)
    val result = (0 to depth).foldLeft(a.tree) { case (t, _) => Select(t, TermName("_1")) }
    c.Expr(result)
  }

  def find[A <: Product, B](a: A): B = macro find_impl[A, B]

  def find_impl[A: c.WeakTypeTag, B: c.WeakTypeTag](c: blackbox.Context)(a: c.Expr[A]): c.Expr[B] = {
    import c.universe._
    val bType: Type = weakTypeOf[B]

    @tailrec
    def moveToSiblingOrBacktrack(curNode: Tree, prevNodes: List[Tree]): (Tree, List[Tree]) = {
      prevNodes.headOption match {
        case None => sys.error(s"This tuple does not contain the searched for type ${bType.typeSymbol.name}")
        case Some(parent) => {
          val indexOfCurrent = parent.children.indexOf(curNode)
          try {
            (parent.children(indexOfCurrent + 1), prevNodes)
          } catch {
            case e: IndexOutOfBoundsException => {
              val tail = prevNodes.tail
              val newCurrent = prevNodes.head
              moveToSiblingOrBacktrack(newCurrent, tail)
            }
          }
        }
      }
    }

    @tailrec
    def findTypeInNestedTuples(currentNode: Tree, previousNodes: List[Tree] = Nil): Tree = {
      if (currentNode.tpe == bType) {
        currentNode
      } else if (isTupleSymbol(currentNode.tpe.typeSymbol.name.toString)) {
        // check if tuple and if it is go deeper
        findTypeInNestedTuples(currentNode.children(1), currentNode :: previousNodes)
      } else {
        // is not tuple and not searched for value, so in parent check next after current node
        // if next after current node does not exist then backtrack
        // if no parent exists then the value does not exist in this tuple
        val (newCur, newPrev) = moveToSiblingOrBacktrack(currentNode, previousNodes)
        findTypeInNestedTuples(newCur, newPrev)
      }
    }

    c.Expr(findTypeInNestedTuples(a.tree, Nil))
  }
}
