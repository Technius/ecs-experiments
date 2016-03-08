package eventbased

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import java.util.UUID
import scala.collection.immutable.Seq

import core.eventbased._

class SystemSpec extends FlatSpec with PropertyChecks with Matchers {

  "Entities with position and velocity" should "move" in {
    forAll { (x: Double, y: Double, vx: Double, vy: Double) =>
      val entities = Seq(
        UUID.randomUUID -> Seq(
          Component.Position(x, y), Component.Velocity(vx, vy, 0)
        )
      )
      val sim = Simulation.simulate(List(Systems.motion)) _
      val Seq(e) = sim(World(), entities)
      val Some(p) = e.components.collectFirst { case c: Component.Position => c }
      p.x should === (x + vx)
      p.y should === (y + vy)
    }
  }
}
