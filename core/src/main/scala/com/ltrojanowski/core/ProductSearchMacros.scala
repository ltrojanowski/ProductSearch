package com.ltrojanowski.core

import scala.annotation.tailrec
import scala.language.experimental.macros
import scala.reflect.macros.whitebox
import scala.reflect.macros.blackbox

trait ProductSearch[A, B] {
  def find(a: A): B
}

object ProductSearch {
  implicit def materializeProductSearch[A, B]: ProductSearch[A, B] = macro ProductSearchMacros.find_impl[A, B]

}

object ProductSearchMacros {

  private def isTupleSymbol(symbol: String) = {
    val isTupleSymbolPattern = "^Tuple(\\d{1,2})$".r
    symbol match {
      case isTupleSymbolPattern(size) if Integer.valueOf(size) < 23 => true
      case _ => false
    }
  }

  def deepLeft[A <: Product](a: A): Any = macro deepLeft_impl[A]

  def deepLeft_impl[A: c.WeakTypeTag](c: whitebox.Context)(a: c.Expr[A]): c.Expr[Any] = {
    import c.universe._

//    c.info(c.enclosingPosition, s"foo: ${showRaw(q"t._1._1")}", false)

    @tailrec
    def findDeepestLeftInNestedTuples(tpe: Type, depth: Int = 0): Int = {
      tpe.dealias.typeArgs.headOption match {
        case Some(t) if isTupleSymbol(t.typeSymbol.name.toString) => findDeepestLeftInNestedTuples(t, depth + 1)
        case Some(t) => depth
        case None => sys.error("Tuple with no elements (´⊙ω⊙`)ʷᵗᶠ")
      }
    }
    val depth = findDeepestLeftInNestedTuples(a.actualType)
    val result = (0 to depth).foldLeft(a.tree) { case (t, _) => Select(t, TermName("_1")) }
    c.Expr(result)
  }

//  def find[A <: Product, B](a: A): B = macro find_impl[A, B]

  def find_impl[A: c.WeakTypeTag, B: c.WeakTypeTag](c: blackbox.Context): c.Expr[ProductSearch[A, B]] = {
    import c.universe._
    val bType: Type = weakTypeOf[B]
    val aType: Type = weakTypeOf[A]

    case class Node(idx: Int, tpe: Type) {
      def children: Vector[Node] = tpe.dealias.typeArgs.zipWithIndex.map { case (t, i) => Node(i, t) }.toVector
      def isTuple: Boolean = isTupleSymbol(tpe.typeSymbol.name.toString)
    }

    @tailrec
    def moveToSiblingOrBacktrack(curNode: Node, prevNodes: List[Node]): (Node, List[Node]) = {
      prevNodes.headOption match {
        case None => sys.error(s"This tuple does not contain the searched for type: ${bType.typeSymbol.fullName}")
        case Some(parent) => {
          try {
            (parent.children(curNode.idx + 1), prevNodes)
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
    def findTypeInNestedTuples(
      currentNode: Node, previousNodes: List[Node] = Nil
    ): List[String] = {
      if (currentNode.tpe == bType) {
        (currentNode :: previousNodes).reverse.tail.map(n => s"_${n.idx + 1}")
      } else if (currentNode.isTuple) {
        // check if tuple and if it is go deeper
        findTypeInNestedTuples(currentNode.children.head, currentNode :: previousNodes)
      } else {
        // is not tuple and not searched for value, so in parent check next after current node
        // if next after current node does not exist then backtrack
        // if no parent exists then the value does not exist in this tuple
        val (newCur, newPrev) = moveToSiblingOrBacktrack(currentNode, previousNodes)
        findTypeInNestedTuples(newCur, newPrev)
      }
    }
    val findImpl =
      findTypeInNestedTuples(Node(0, aType))
        .foldLeft(Ident(TermName("a")): Tree) {
          case (tree, term) => Select(tree, TermName(term))
        }

    reify {
      new ProductSearch[A, B] {
        def find(a: A): B = c.Expr[B](findImpl).splice
      }
    }
  }
}
