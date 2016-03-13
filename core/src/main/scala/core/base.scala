package core

import scala.language.higherKinds

/**
 * Defines characteristics shared across all simulations
 */
trait Base {
  /**
   * The type used to identify an entity
   */
  type Id

  /**
   * The type of each component
   */
  type Component

  /**
   * The collection used to store components
   */
  type ComponentCol <: Seq[Component]

  /**
   * Defines the entity type
   */
  type Entity = (Id, ComponentCol)

  /**
   * The collection used to store entities
   */
  type EntityCol <: Seq[Entity]

  /**
   * The type of each system
   */
  type System <: EntityCol => _
}
