package com.example

import com.example.macros.MacroExamples
import org.scalatest.{FlatSpec, Matchers}

object A {
  def unapply(x: Int): Option[Int] = Some(x)
}

class MacroExamplesSpec extends FlatSpec with Matchers {
  behavior of "remove guard"

  it should "remove guard from a simple partial function" in {
    val orig: PartialFunction[Int, Int] = { case x if x > 0 ⇒ x + 1 }

    orig(123) shouldEqual 124
    orig.isDefinedAt(0) shouldEqual false

    val removedGuard = MacroExamples.removeGuard[Int, Int] { case x if x > 0 ⇒ x + 1 }

    removedGuard(123) shouldEqual 124
    removedGuard(0) shouldEqual 1
  }

  it should "remove guard from a partial function with unapply" in {
    val removedGuard = MacroExamples.removeGuard[Int, Int] { case A(x) if x > 0 ⇒ x + 1 }

    removedGuard(123) shouldEqual 124
    removedGuard(0) shouldEqual 1
  }

}
