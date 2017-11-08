package com.example

import com.example.macros.QuasiquotesExamples
import org.scalatest.{FlatSpec, Matchers}

class QuasiquotesExamplesSpec extends FlatSpec with Matchers  {
  behavior of "detecting literal Int"

  it should "detect literal Int correctly" in {
    QuasiquotesExamples.matchLiteralInt(1) shouldEqual Some(1)
    QuasiquotesExamples.matchLiteralInt(1+1) shouldEqual Some(2)
    val a = 1
    QuasiquotesExamples.matchLiteralInt(a) shouldEqual None
    QuasiquotesExamples.matchLiteralInt(a+1) shouldEqual None
  }
}
