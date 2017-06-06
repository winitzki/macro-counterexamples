scalaVersion := "2.12.2"

crossScalaVersions := Seq("2.11.8", "2.12.2")

scalacOptions ++= Seq(
  /*  "-Yshow-syms"
    , "-Ymacro-debug-verbose" // -Ymacro-debug-lite
    , "-Xprint:typer"
    , "-Xprint-types"
    , "-uniqid"
    */
)

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
  , "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided"
  , "org.scalatest" %% "scalatest" % "3.0.1" % Test
)
