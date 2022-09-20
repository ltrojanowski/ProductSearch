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

  def find[A, B](a: A)(implicit ps: ProductSearch[A, B]): B = ps.find(a)

  test("return correct value of ProductSearch.find") {
    val fooTuple = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
    val actual: Foo = find(fooTuple)(implicitly[ProductSearch[(((Foo, Int), String), String, Double), Foo]])
    assert(actual == Foo("asdf"))
  }

  test("return correct value for Tuple3Syntax") {
    import ProductSearchSyntax._
    val fooTuple = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
    val actual = fooTuple.find[Foo]
    assert(actual == Foo("asdf"))
  }

//  test("return value of ProductSearch.deepLeft syntax") {
//    import ProductSearch.Syntax._
//    val fooTuple = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
//    val actual = fooTuple.deepLeft
//    assert(actual == Foo("asdf"))
//  }
  

//  test("return value of ProductSearch.find[Foo]") {
//    val foo = Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), "asdf"), "asdf", 1.0)
//    val actual: Foo =
//      ProductSearchMacros
//        .find[(((Foo, Int), String), String, Double), Foo](foo)
//    assert(actual == Foo("asdf"))
//  }
//
//  test("return value of ProductSearch.find[Bar]") {
//    val actual: Bar =
//      ProductSearchMacros
//        .find[(((Foo, Int), Bar), String, Double), Bar](
//          Tuple3(Tuple2(Tuple2(Foo("asdf"), 1), Bar(2)), "asdf", 1.0)
//        )
//    assert(actual == Bar(2))
//  }
//
  test("return value of ProductSearchSyntax find[Baz]") {
    assert(
      compileErrors(
        "import ProductSearchSyntax._; val tuple = Tuple3(Tuple2(Tuple2(Foo(\"asdf\"), 1), Bar(2)), \"asdf\", 1.0); tuple.find[Baz]")
        .contains("This tuple does not contain the searched for type: com.ltrojanowski.ProductSearchTest.Baz"))
  }
}
