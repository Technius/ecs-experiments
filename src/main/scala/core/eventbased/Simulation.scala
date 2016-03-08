package core.eventbased

import scala.collection.immutable.Seq

object Simulation extends core.Simulation[Entity, World, System, Seq] {
  def simulate(systems: List[System])(world: World, entities: Seq[Entity]): Seq[Entity] = {
    val start: SimOutput = (Seq.empty, Seq.empty)
    val events = systems.map(f => f(entities)).reduce(_ ++ _)
    EventHandler.handle(entities, events)
  }
}
