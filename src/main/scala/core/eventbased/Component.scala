package core.immutable

sealed trait Component
object Component {
  case class Position(x: Double, y: Double, angle: Double = 0) extends Component
  case class Velocity(x: Double, y: Double, angle: Double) extends Component
}
