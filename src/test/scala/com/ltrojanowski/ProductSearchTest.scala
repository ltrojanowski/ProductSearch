package com.ltrojanowski

class ProductSearchTest extends munit.FunSuite {

  case class Foo(f: String)

  test("return value of ProductSearch.first") {
    val actual: Foo = ProductSearch.deepLeft(Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0))
    assert(actual == Foo("asdf"))
  }
}
