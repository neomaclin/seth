name := "seth"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= akka ++ Seq(
  "org.typelevel" %% "cats-core" % "1.0.0-MF",
  "org.typelevel" %% "cats-free" % "1.0.0-MF",
  "org.typelevel" %% "dogs-core" % "0.6.9",
  "com.chuusai" %% "shapeless" % "2.3.2",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "com.github.alexandrnikitin" %% "bloom-filter" % "latest.release"
)

lazy val akka = {
  val akkaVersion = "2.5.6"
  val httpVersion = "10.0.10"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % httpVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % httpVersion % Test,
    "org.iq80.leveldb"            % "leveldb"          % "0.9",
    "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"
  )
}