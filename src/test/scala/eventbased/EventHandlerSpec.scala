package eventbased

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import java.util.UUID
import scala.collection.immutable.Seq

import core.Implicits._
import core.eventbased._

class EventHandlerSpec extends FlatSpec with PropertyChecks with Matchers {

  "Entities" should "spawn" in {
    val comps = Seq()
    val handled = EventHandler.handleWorld(Seq.empty, Seq(WorldEvent.Spawn(comps)))
    handled.isEmpty should not be true
  }

  it should "die" in {
    val id = UUID.randomUUID
    val e = id -> Seq()
    val handled = EventHandler.handleWorld(Seq(e), Seq(WorldEvent.Die(id)))
    handled.isEmpty should be (true)
  }

  "Motion" should "be handled" in {
    forAll { (x: Double, y: Double, dx: Double, dy: Double) =>
      val entity = UUID.randomUUID -> Seq(Component.Position(x, y))
      val event = EntityEvent.Motion(dx, dy, 0)
      val res = EventHandler.handleEntityEvent(entity, event)
      val Some(p) = res.components.collectFirst { case p: Component.Position => p }
      p.x should be (x + dx)
      p.y should be (y + dy)
    }
  }

  it must "combine commutatively" in {
    val entity = UUID.randomUUID -> Seq(Component.Position(0, 0))
    forAll { (dx1: Double, dy1: Double, dx2: Double, dy2: Double) =>
      val event1 = EntityEvent.Motion(dx1, dy1, 0)
      val event2 = EntityEvent.Motion(dx2, dy2, 0)
      val run = EventHandler.handleEntityEvent _

      def extract(e: Entity) =
        e.components.collectFirst { case p: Component.Position => p }

      val Some(p1) = extract(run(run(entity, event1), event2))
      val Some(p2) = extract(run(run(entity, event2), event1))
      p1 should === (p2)
    }
  }
}
