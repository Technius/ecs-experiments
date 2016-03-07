# Entity-Component-System Experiments (in Scala)
This repository contains some plans for writing a game/simulation engine in
Scala based on entity-component-systems.

## Experiment List

In each experiment, an **entity** is defined as an `Id` and a list of
`Component`s.

A **world** is a set of global rules.

### 1. Event Based
Inspired by Michael Shaw's
[Game Development in Scala talk](https://michaelshaw.github.io/game_talk/game.html#/).

The design of this ECS is based on the following ideas:
* The simulation is deterministic
* Everything is immutable
* During simulation, each system operates on the pre-simulation entity list to
  give events as output
* After the systems are run, the events are processed and the new entity list is
  returned
* Events are divided into `EntityEvent` and `WorldEvent`
  * An `EntityEvent` is applied to a specific entity
    entity
  * A `WorldEvent` is applied to the list of entities
* Systems are essentially `Seq[Entity] => (Seq[EntityEvent], Seq[WorldEvent])`

Benefits:
* Events that can be applied associately can be combined, making transfer of
  events take less bandwidth
* All systems can be run in parallel
* Separation of events into `EntityEvent` and `WorldEvent` limit side-effects

Disadvantages:
* Systems cannot depend on other systems easily

## License
The explanations and documentation are licensed under the
[CC BY 4.0](https://creativecommons.org/licenses/by/4.0/legalcode) license.

The code is licensed under the GPLv3 license. See LICENSE for more details.
