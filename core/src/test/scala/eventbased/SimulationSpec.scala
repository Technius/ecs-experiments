package eventbased

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import java.util.UUID
import scala.collection.immutable.Seq

import core.eventbased._

class SimulationSpec extends FlatSpec with PropertyChecks with Matchers {

  def world = World()

  def spawnSystem: System = (entities: Seq[Entity]) => {
    val worldEvents = Seq[WorldEvent](
      WorldEvent.Spawn(Seq.empty)
    )
    (Seq.empty, worldEvents)
  }

  "A simulation" should "run (assuming WorldEvent.Spawn is handled)" in {
    val entities = Seq.empty[Entity]
    val sim = Simulation.simulate(List(spawnSystem)) _
    val newEntities = sim(world, entities)
    val newEntities2 = sim(world, newEntities)
    entities should not be newEntities
    newEntities should not be newEntities2
  }
}
