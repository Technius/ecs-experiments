package core.immutable

sealed trait Event

sealed trait EntityEvent extends Event

object EntityEvent {
  case class Motion(dx: Double, dy: Double, dangle: Double) extends EntityEvent
}

sealed trait WorldEvent extends Event

object WorldEvent {
  case class Spawn(entity: Seq[Component]) extends WorldEvent
  case class Die(id: Id) extends WorldEvent
}
