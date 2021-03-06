package com.example.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

class Macros(val c: blackbox.Context) {
  import c.universe._

  def removeGuardImpl[A, B](pf: c.Expr[PartialFunction[A, B]]): Tree = {
    val q"{ case $pattern if $guard => $body }" = pf.tree
    q"{ case $pattern => $body }"
  }

  def leaveGuardImpl[A, B](pf: c.Expr[PartialFunction[A, B]]): Tree = {
    val q"{ case $pattern if $guard => $body }" = pf.tree
    q"{ case $pattern => $body }"
  }

  def insertGuardImpl[A, B](pf: c.Expr[PartialFunction[A, B]]): Tree = {
    val q"{ case $pattern => $body }" = pf.tree
    q"{ case $pattern if true => $body }"
  }

  def noInsertGuardImpl[A, B](pf: c.Expr[PartialFunction[A, B]]): Tree = {
    val q"{ case $pattern => $body }" = pf.tree
    q"{ case $pattern if true => $body }"
  }
}

object MacroExamples {

  def removeGuard[A, B](pf: PartialFunction[A, B]): PartialFunction[A, B] = macro Macros.removeGuardImpl[A, B]

  def leaveGuard[A, B](pf: PartialFunction[A, B]): PartialFunction[A, B] = macro Macros.leaveGuardImpl[A, B]

  def noInsertGuard[A, B](pf: PartialFunction[A, B]): PartialFunction[A, B] = macro Macros.noInsertGuardImpl[A, B]

  def insertGuard[A, B](pf: PartialFunction[A, B]): PartialFunction[A, B] = macro Macros.insertGuardImpl[A, B]
}