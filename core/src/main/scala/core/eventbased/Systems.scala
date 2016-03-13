package core.eventbased

import scala.collection.immutable.Seq

import core.Implicits._

object Systems {
  val motion: System = { (entities: Seq[Entity]) =>
    val events = for {
      e <- entities
      v <- e.componentOf[Component.Velocity]
    } yield {
      e.id -> EntityEvent.Motion(v.x, v.y, 0)
    }
    (events, Seq.empty)
  }
}
