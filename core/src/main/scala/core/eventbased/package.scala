package core

import java.util.UUID
import scala.collection.immutable.Seq

package object eventbased {
  type Id = BaseImpl.Id
  type Entity = BaseImpl.Entity
  type System = BaseImpl.System
  type SimOutput = (Seq[(Id, EntityEvent)], Seq[WorldEvent])

  implicit class SimOutputOps(val o: SimOutput) extends AnyVal {
    @inline def entityEvents: Seq[(Id, EntityEvent)] = o._1
    @inline def worldEvents: Seq[WorldEvent] = o._2
    @inline def ++(o2: SimOutput): SimOutput = (o._1 ++ o2._1, o._2 ++ o2._2)
  }

  object BaseImpl extends Base {
    type Id = UUID
    type Component = core.eventbased.Component
    type ComponentCol = Seq[Component]
    type EntityCol = Seq[Entity]
    type System = EntityCol => SimOutput
  }
}
