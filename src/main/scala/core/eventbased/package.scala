package core

import java.util.UUID
import scala.collection.immutable.Seq

package object eventbased {
  type Id = UUID
  type Entity = (Id, Seq[Component])
  type System = (Seq[Component], Seq[Event])
  type SimOutput = (Seq[(Id, EntityEvent)], Seq[WorldEvent])

  implicit class EntityOps(val e: Entity) extends AnyVal {
    @inline def id: Id = e._1
    @inline def components: Seq[Component] = e._2
  }
}
