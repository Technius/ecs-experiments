package core.eventbased

import scala.collection.immutable.Seq

object Systems {
  val motion: System = { (entities: Seq[Entity]) =>
    val events = for {
      e <- entities
      v <- e.components.collectFirst { case c: Component.Velocity => c } // TODO: Write macro for this
    } yield {
      e.id -> EntityEvent.Motion(v.x, v.y, 0)
    }
    (events, Seq.empty)
  }
}
