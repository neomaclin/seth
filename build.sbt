name := "seth"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "0.9.0",
  "org.typelevel" %% "cats-free" % "0.9.0",
  "com.chuusai" %% "shapeless" % "2.3.2",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test"
)