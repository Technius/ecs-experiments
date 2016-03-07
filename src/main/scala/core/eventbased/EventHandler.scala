package core.immutable

import java.util.UUID
import scala.collection.immutable.Seq

object EventHandler {
  def handle(entities: Seq[Entity], events: SimOutput): Seq[Entity] =  {
    val (entityEvents, worldEvents) = events
    val updEntities = handleEntities(entities, entityEvents)
    val updWorld = handleWorld(entities, worldEvents)
    updWorld
  }

  def handleEntities(entities: Seq[Entity], events: Seq[(Id, EntityEvent)]): Seq[Entity] = {
    val evMap = events.groupBy(_._1).toMap.mapValues(seq => seq.map(_._2))
    entities map { e =>
      evMap.get(e.id) match {
        case Some(ev3) =>
          ev3.foldLeft(e)((curE, nextEv) => handleEntityEvent(curE, nextEv))
        case None => e
      }
    }
  }

  def handleEntityEvent(entity: Entity, event: Event): Entity = event match {
    case EntityEvent.Motion(dx, dy, dangle) =>
      val p = entity.components.collectFirst { case p: Component.Position => p }
      upd(entity) {
        case p: Component.Position =>
          Some(p.copy(x = p.x + dx, y = p.y + dy, angle = p.angle + p.angle))
      }
    case _ => entity
  }

  def handleWorld(entities: Seq[Entity], events: Seq[WorldEvent]): Seq[Entity] = {
    import WorldEvent._
    events.foldLeft(entities)((e, event) => event match {
      case Spawn(comps: Seq[Component]) => e :+ (UUID.randomUUID -> comps) // TODO: Make deterministic
      case Die(id: Id) => e.filterNot(_.id == id)
      case _ => e
    })
  }

  private[this] def upd(entity: Entity)(f: PartialFunction[Component, Option[Component]]) = {
    val comps = entity.components flatMap { c =>
      if (f.isDefinedAt(c)) f(c)
      else Some(c)
    }
    entity.id -> comps
  }
}
