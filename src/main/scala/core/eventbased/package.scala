package core

import java.util.UUID
import scala.collection.immutable.Seq

package object eventbased {
  type Id = UUID
  type Entity = (Id, Seq[Component])
  type System = Seq[Entity] => SimOutput
  type SimOutput = (Seq[(Id, EntityEvent)], Seq[WorldEvent])

  implicit class EntityOps(val e: Entity) extends AnyVal {
    @inline def id: Id = e._1
    @inline def components: Seq[Component] = e._2
  }

  implicit class SimOutputOps(val o: SimOutput) extends AnyVal {
    @inline def entityEvents: Seq[(Id, EntityEvent)] = o._1
    @inline def worldEvents: Seq[WorldEvent] = o._2
    @inline def ++(o2: SimOutput): SimOutput = (o._1 ++ o2._1, o._2 ++ o2._2)
  }
}
