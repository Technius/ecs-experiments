package core

import scala.language.higherKinds

/**
 * Contains methods for performing a simulation.
 * @tparam B The ECS simulation base type
 * @tparam W The world type
 */
trait Simulation[B <: Base, W] {
  def simulate(systems: List[B#System])(world: W, entities: B#EntityCol): B#EntityCol
}
