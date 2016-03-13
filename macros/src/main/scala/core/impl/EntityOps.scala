package core.impl

import scala.reflect.macros.blackbox.Context

object EntityOps {
  def componentOf[T: c.WeakTypeTag](c: Context): c.Expr[Option[T]] = {
    import c.universe._
    val tpe = c.weakTypeOf[T]
    val e = c.prefix.tree
    val src = q"$e.components collectFirst { case c: $tpe => c }"
    c.Expr[Option[T]](src)
  }
}
