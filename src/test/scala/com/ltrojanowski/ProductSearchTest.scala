package com.ltrojanowski

import com.ltrojanowski.core.{ProductSearch, ProductSearchMacros}

class ProductSearchTest extends munit.FunSuite {

  case class Foo(f: String)
  case class Bar(i: Int)
  case class Baz(j: Double)

  test("return value of ProductSearch.deepLeft") {
    val fooTuple = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
    val actual = ProductSearchMacros.deepLeft(fooTuple)
    assert(actual == Foo("asdf"))
  }

  test("return correct value of implicitly[ProductSearch[_]].find") {
    val fooTuple = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
    val actual = implicitly[ProductSearch[(((Foo, Int), String), String, Double), Foo]].find(fooTuple)
    assert(actual == Foo("asdf"))
  }

  import ProductSearchSyntax._

  test("return find[Foo] value for Tuple1Syntax") {
    val fooTuple = Tuple1(Tuple2(Tuple2(Foo("asdf"), 1), Foo("fdsa")))
    val actual = fooTuple.find[Foo]
    assert(actual == Foo("asdf"))
  }

  test("return find[Foo] value for Tuple2Syntax") {
    val fooTuple = Tuple2(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), Foo("fdsa"))
    val actual = fooTuple.find[Foo]
    assert(actual == Foo("asdf"))
  }

  test("return find[Foo] value for Tuple3Syntax") {
    val fooTuple = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), Foo("fdsa"), 1.0)
    val actual = fooTuple.find[Foo]
    assert(actual == Foo("asdf"))
  }

  test("return find[Foo] value for Tuple4Syntax") {
    val fooTuple = Tuple4(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), Foo("fdsa"), 1.0, Bar(2))
    val actual = fooTuple.find[Foo]
    assert(actual == Foo("asdf"))
  }

  test("return find[Foo] value for Tuple5Syntax") {
    val fooTuple = Tuple5(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), Foo("fdsa"), 1.0, Bar(2), Bar(3))
    val actual = fooTuple.find[Foo]
    assert(actual == Foo("asdf"))
  }

  test("return find[Bar] in nested tuple") {
    val barTuple = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), ("asdf", (1.0, (Bar(2), "fdas")))), "asdf", 1.0)
    val actual: Bar = barTuple.find[Bar]
    assert(actual == Bar(2))
  }

  test("return error when trying to find[Baz] where Baz is not in tuple") {
    assert(
      compileErrors(
        "import ProductSearchSyntax._; val tuple = Tuple3(Tuple2(Tuple2(Foo(\"asdf\"), 1), Bar(2)), \"asdf\", 1.0); tuple.find[Baz]")
        .contains("This tuple does not contain the searched for type: com.ltrojanowski.ProductSearchTest.Baz"))
  }
}
