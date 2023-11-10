ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "akka_scala_seed"
  )


libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.1.0"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.33"
libraryDependencies += "com.github.tototoshi" %% "slick-joda-mapper" % "2.6.0"
libraryDependencies += "joda-time" % "joda-time" % "2.12.5"

libraryDependencies +="com.typesafe.akka" %% "akka-http" % "10.2.6"
libraryDependencies +="com.typesafe.akka" %% "akka-http-spray-json" % "10.2.6"
libraryDependencies +="com.typesafe.akka" %% "akka-stream" % "2.8.0"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.8.0"
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0"
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.8.0"
libraryDependencies += "com.typesafe.akka" %% "akka-serialization-jackson" % "2.8.0"