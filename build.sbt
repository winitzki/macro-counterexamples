scalaVersion := "2.12.2"

crossScalaVersions := Seq("2.11.8", "2.12.2")

scalaCOptions ++= Seq()

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
  , "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided"
  , "org.scalatest" %% "scalatest" % "3.0.1" % Test
)
