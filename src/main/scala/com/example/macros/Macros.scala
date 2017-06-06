package com.example.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

class Macros(val c: blackbox.Context) {
  import c.universe._

  def removeGuardImpl[A, B](pf: c.Expr[PartialFunction[A, B]]): Tree = {
    val q"{ case $pattern if $guard => $body }" = pf.tree
    q"{ case $pattern => $body }"
  }
}

object MacroExamples {

  def removeGuard[A, B](pf: PartialFunction[A, B]): PartialFunction[A, B] = macro Macros.removeGuardImpl[A, B]

}