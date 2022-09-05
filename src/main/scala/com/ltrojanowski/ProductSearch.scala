package com.ltrojanowski

import scala.annotation.tailrec
import scala.language.experimental.macros
import scala.reflect.macros.whitebox

object ProductSearch {

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

    @tailrec
    def findDeepestLeftInNestedTuples(tr: Tree, depth: Int = 0): Tree = {
      tr.children.tail.headOption match {
        case Some(t) if (isTupleSymbol(t.tpe.typeSymbol.name.toString)) => findDeepestLeftInNestedTuples(t, depth + 1)
        case Some(t) => t
        case None => sys.error("Tuple with no elements (´⊙ω⊙`)ʷᵗᶠ")
      }
    }
    val deepElem = findDeepestLeftInNestedTuples(a.tree)
    c.Expr(deepElem)
  }
}
