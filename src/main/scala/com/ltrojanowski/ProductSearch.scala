package com.ltrojanowski

import com.ltrojanowski.core.ProductSearchMacros

import scala.annotation.tailrec
import scala.language.experimental.macros
import scala.reflect.macros.whitebox
import scala.reflect.macros.blackbox

object ProductSearch {

  object Syntax {
    implicit class Tuple3Syntax[A1, A2, A3](a: (A1, A2, A3)) {
      def deepLeft = ProductSearchMacros.deepLeft(a)
    }
  }
}
