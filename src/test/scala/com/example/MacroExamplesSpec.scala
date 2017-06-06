package com.example

import com.example.macros.MacroExamples
import org.scalatest.{FlatSpec, Matchers}

object A {
  def unapply(x: Int): Option[Int] = Some(x)
}

class MacroExamplesSpec extends FlatSpec with Matchers {

  behavior of "macro"

  it should "remove guard from a simple partial function" in {
    val orig: PartialFunction[Int, Int] = { case x if x > 0 ⇒ x + 1 }

    orig(123) shouldEqual 124
    orig.isDefinedAt(0) shouldEqual false

    val removedGuard = MacroExamples.removeGuard[Int, Int] { case x if x > 0 ⇒ x + 1 }

    removedGuard(123) shouldEqual 124
    removedGuard(0) shouldEqual 1
  }

  it should "leave guard in place from a simple partial function" in {
    val keptGuard = MacroExamples.leaveGuard[Int, Int] { case x if x > 0 ⇒ x + 1 }

    keptGuard(123) shouldEqual 124
    keptGuard(0) shouldEqual 1
  }

  it should "remove guard from a partial function with unapply" in {
    val removedGuard = MacroExamples.removeGuard[Int, Int] { case A(x) if x > 0 ⇒ x + 1 }

    removedGuard(123) shouldEqual 124
    removedGuard(0) shouldEqual 1
  }

  it should "leave guard in place from a partial function with unapply" in {
    val removedGuard = MacroExamples.removeGuard[Int, Int] { case A(x) if x > 0 ⇒ x + 1 }

    removedGuard(123) shouldEqual 124
    removedGuard(0) shouldEqual 1
  }

  /* These tests cause an error:

  Error:scalac: Error: Could not find proxy for val o7: Option in List(value o7, method applyOrElse, <$anon: Function1>, value removedGuard, method $anonfun$new$2, value <local MacroExamplesSpec>, class MacroExamplesSpec, package example, package com, package <root>) (currentOwner= value x )

    java.lang.IllegalArgumentException: Could not find proxy for val o7: Option in List(value o7, method applyOrElse, <$anon: Function1>, value removedGuard, method $anonfun$new$2, value <local MacroExamplesSpec>, class MacroExamplesSpec, package example, package com, package <root>) (currentOwner= value x )
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:310)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.$anonfun$proxy$4(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.$anonfun$proxy$4(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.$anonfun$proxy$4(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.$anonfun$proxy$4(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.$anonfun$proxy$4(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.$anonfun$proxy$4(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.$anonfun$proxy$4(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.$anonfun$proxy$4(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.$anonfun$proxy$4(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.$anonfun$proxy$4(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.searchIn$1(LambdaLift.scala:315)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.proxy(LambdaLift.scala:324)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.proxyRef(LambdaLift.scala:364)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.postTransform(LambdaLift.scala:519)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.transform(LambdaLift.scala:549)
      at scala.tools.nsc.transform.LambdaLift$LambdaLifter.transform(LambdaLift.scala:51)
      at scala.reflect.internal.Trees.itransform(Trees.scala:1349)
      at scala.reflect.internal.Trees.itransform$(Trees.scala:1340)
      at scala.reflect.internal.SymbolTable.itransform(SymbolTable.scala:16)

      ...
  */
}
