package core

import scala.language.higherKinds

/**
 * Contains methods for performing a simulation.
 * @tparam E The entity type
 * @tparam W The world type
 * @tparam S The system type
 * @tparam C The collection type used to store the entities
 */
trait Simulation[E, W, S, C[_]] {
  def simulate(systems: List[S])(world: W, entities: C[E]): C[E]
}
