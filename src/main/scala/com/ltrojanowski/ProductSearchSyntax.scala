package com.ltrojanowski

import com.ltrojanowski.core.ProductSearch

import scala.language.experimental.macros


object ProductSearchSyntax {

  implicit class Tuple1Syntax[A <: Tuple1[_]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple2Syntax[A <: (_, _)](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple3Syntax[A <: (_, _, _)](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple4Syntax[A <: (_, _, _, _)](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple5Syntax[A <: (_, _, _, _, _)](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple6Syntax[A <: (_, _, _, _, _, _)](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple7Syntax[A <: (_, _, _, _, _, _, _)](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple8Syntax[A <: (_, _, _, _, _, _, _, _)](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple9Syntax[A <: (_, _, _, _, _, _, _, _, _)](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple10Syntax[A <: Tuple10[_, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple11Syntax[A <: Tuple11[_, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple12Syntax[A <: Tuple12[_, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple13Syntax[A <: Tuple13[_, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple14Syntax[A <: Tuple14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple15Syntax[A <: Tuple15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple16Syntax[A <: Tuple16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple17Syntax[A <: Tuple17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple18Syntax[A <: Tuple18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }


  implicit class Tuple19Syntax[A <: Tuple19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple20Syntax[A <: Tuple20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple21Syntax[A <: Tuple21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Tuple22Syntax[A <: Tuple22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

}
