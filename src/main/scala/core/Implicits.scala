package core

/**
 * Contains genericized implicit utilities.
 */
object Implicits {
  implicit class EntityOps[B <: Base](val e: B#Entity) extends AnyVal {
    @inline def id: B#Id = e._1
    @inline def components: B#ComponentCol = e._2
  }
}
