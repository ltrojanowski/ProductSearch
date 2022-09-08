package com.ltrojanowski

class ProductSearchTest extends munit.FunSuite {

  case class Foo(f: String)

  test("return value of ProductSearch.first") {
    val fooTpe = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
    val actual: Foo = ProductSearch.deepLeft(fooTpe)
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
}
