package core

import scala.language.experimental.macros

/**
 * Contains genericized implicit utilities.
 */
object Implicits {
  implicit class EntityOps[B <: Base](val e: B#Entity) extends AnyVal {
    @inline def id: B#Id = e._1
    @inline def components: B#ComponentCol = e._2

    def componentOf[C <: B#Component]: Option[C] =
      macro impl.EntityOps.componentOf[C]
  }
}
