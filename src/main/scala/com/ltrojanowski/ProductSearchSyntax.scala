package com.ltrojanowski

import com.ltrojanowski.core.ProductSearch

import scala.annotation.tailrec
import scala.language.experimental.macros
import scala.reflect.macros.whitebox
import scala.reflect.macros.blackbox



object ProductSearchSyntax {

    implicit class Tuple3Syntax[A <: (_, _, _)](a: A) {
      def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
    }

}
