package com.ltrojanowski

import com.ltrojanowski.core.ProductSearchMacros

class ProductSearchTest extends munit.FunSuite {

  case class Foo(f: String)
  case class Bar(i: Int)

  test("return value of ProductSearch.deepLeft") {
    val fooTuple = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
    val actual = ProductSearchMacros.deepLeft(fooTuple)
    assert(actual == Foo("asdf"))
  }

  test("return value of ProductSearch.deepLeft syntax") {
    import ProductSearch.Syntax._
    val fooTuple = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
    val actual = fooTuple.deepLeft
    assert(actual == Foo("asdf"))
  }

  test("return value of ProductSearch.find[Foo]") {
    val actual: Foo =
      ProductSearchMacros
        .find[(((Foo, Int), String), String, Double), Foo](
          Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
        )
    assert(actual == Foo("asdf"))
  }

  test("return value of ProductSearch.find[Bar]") {
    val actual: Bar =
      ProductSearchMacros
        .find[(((Foo, Int), Bar), String, Double), Bar](
          Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), Bar(2)), "asdf", 1.0)
        )
    assert(actual == Bar(2))
  }
}
