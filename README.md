# product-search
## Description
This library allows for finding values in nested products via it's type. This has been useful to me in the context of a spark application with large joins based on RDD's.

## Example
```scala
import com.ltrojanowski.ProductSearchSyntax._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Main {

  implicit val spark: SparkSession = SparkSession.builder()
    .appName("Simple Application")
    .config("spark.master", "local")
    .getOrCreate()

  case class Foo(a: String, b: Int, c: Double)
  case class Boo(a: String, b: Int, c: Double)
  case class Baz(a: String, b: Int, c: Double)
  case class Bar(a: String, b: Int, c: Double)

  val baseList = List(("a", 1, 1.0), ("b", 2, 2.0), ("c", 3, 3.0))

  val foo: RDD[Foo] = spark.sparkContext.makeRDD(baseList.map(Foo.tupled))
  val boo: RDD[Boo] = spark.sparkContext.makeRDD(baseList.map(Boo.tupled))
  val bar: RDD[Bar] = spark.sparkContext.makeRDD(baseList.map(Bar.tupled))
  val baz: RDD[Baz] = spark.sparkContext.makeRDD(baseList.map(Baz.tupled))

  implicit class KeyedRDDSyntax[K, T](rdd: RDD[(K, T)]) {
    def reKey[NewK](f: T => NewK): RDD[(NewK, T)] = rdd.map { case (_, t) => (f(t), t) }
  }
  implicit class RDDSyntax[T](rdd: RDD[T]) {
    def key[K](f: T => K): RDD[(K, T)] = rdd.map(t => (f(t), t))
  }

  def main(args: Array[String]): Unit = {
    val bazList =
      foo
        .key(_.a)
        .join(boo.key(_.a))
        .reKey(_.find[Boo].b)
        .join(bar.key(_.b))
        .reKey(_.find[Foo].c)
        .join(baz.key(_.c))
        .map(_.find[Baz])
        .collect()
        .toList

    println(bazList.mkString("bazList: \n - ", "\n - ", ""))
  }
}
```
The following code will print
```
bazList: 
 - Baz(b,2,2.0)
 - Baz(c,3,3.0)
 - Baz(a,1,1.0)
```
## Dependencies
I didn't publish this library to maven. The easiest way to get it is via jitpack.
```
resolvers += "jitpack.io" at "https://jitpack.io",
libraryDependencies ++= Seq(
  "com.github.ltrojanowski.product-search" % "product-search_2.13" % "fed943fc73",
  "com.github.ltrojanowski.product-search" % "product-search-core_2.13" % "fed943fc73"
)
```
