package com.example.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

class Quasiquotes(val c: blackbox.Context) {
  import c.universe._

  def matchLiteralInt(iexpr: c.Expr[Int]): c.Expr[Option[Int]] = {
    // match the integer, throw error if zero

    // This is the example from https://docs.scala-lang.org/overviews/quasiquotes/unlifting.html,
    // but IntelliJ thinks the types are incorrect here.
//    val q"${left: Int} + ${right: Int}" = q"2 + 2"

    c.Expr[Option[Int]](iexpr.tree match {
      case q"${x: Int}" ⇒ q"Some($x)"
      case _ ⇒ q"None"
    })

  }
}

object QuasiquotesExamples {
  def matchLiteralInt(iexpr: Int): Option[Int] = macro Quasiquotes.matchLiteralInt
}
