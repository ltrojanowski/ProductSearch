package com.ltrojanowski

import com.ltrojanowski.core.ProductSearch

import scala.language.experimental.macros

object ProductSearchSyntax {

  implicit class Product1Syntax[A <: Product1[_]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product2Syntax[A <: Product2[_, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product3Syntax[A <: Product3[_, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product4Syntax[A <: Product4[_, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product5Syntax[A <: Product5[_, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product6Syntax[A <: Product6[_, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product7Syntax[A <: Product7[_, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product8Syntax[A <: Product8[_, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product9Syntax[A <: Product9[_, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product10Syntax[A <: Product10[_, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product11Syntax[A <: Product11[_, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product12Syntax[A <: Product12[_, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product13Syntax[A <: Product13[_, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product14Syntax[A <: Product14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product15Syntax[A <: Product15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product16Syntax[A <: Product16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product17Syntax[A <: Product17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product18Syntax[A <: Product18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product19Syntax[A <: Product19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product20Syntax[A <: Product20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product21Syntax[A <: Product21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](a: A) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

  implicit class Product22Syntax[A <: Product22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](
      a: A
  ) {
    def find[B](implicit ps: ProductSearch[A, B]): B = ps.find(a)
  }

}
