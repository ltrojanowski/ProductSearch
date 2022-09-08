package com.ltrojanowski

class ProductSearchTest extends munit.FunSuite {

  case class Foo(f: String)
  case class Bar(i: Int)

  test("return value of ProductSearch.first") {
    val fooTuple = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
    val actual: Foo = ProductSearch.deepLeft(fooTuple)
    assert(actual == Foo("asdf"))
  }

  test("return value of ProductSearch.find[Foo]") {
    val actual: Foo =
      ProductSearch
        .find[(((Foo, Int), String), String, Double), Foo](
          Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
        )
    assert(actual == Foo("asdf"))
  }

  test("return value of ProductSearch.find[Bar]") {
    val actual: Bar =
      ProductSearch
        .find[(((Foo, Int), Bar), String, Double), Bar](
          Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), Bar(2)), "asdf", 1.0)
        )
    assert(actual == Bar(2))
  }
}
