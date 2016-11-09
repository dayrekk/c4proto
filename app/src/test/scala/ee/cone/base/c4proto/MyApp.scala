
package ee.cone.base.c4proto

object MyApp extends App {
  import MySchema._
  val leader0 = Person("leader0", Some(40))
  val worker0 = Person("worker0", Some(30))
  val worker1 = Person("worker1", Some(20))
  val group0 = Group(Some(leader0), List(worker0,worker1))
  val findAdapter = new FindAdapter(MySchema)()
  val adapter = findAdapter(group0)
  val bytes = adapter.encode(group0)
  println(bytes.toList)
  //println(new String(bytes))
  val group1 = adapter.decode(bytes)
  println(group0,group1,group0==group1)
/*
  com.squareup.wire.ProtoAdapter
  classOf[String].getName

  trait Lens[M,V] {
    def get(model: M): V
    def set(model: M, value: V): M
  }

  def lens[M,V](get: M⇒V): Lens[M,V]

  change(model.name, "Leader")
*/
}

@schema object MySchema extends Schema {
  @Id(0x0003) case class Person(@Id(0x0007) name: String, @Id(0x0004) age: Option[BigDecimal] @scale(0))
  @Id(0x0001) case class Group(@Id(0x0005) leader: Option[Person], @Id(0x0006) worker: List[Person])
}