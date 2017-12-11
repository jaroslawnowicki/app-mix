name := "notification system"

version := "1.0"

lazy val `notification` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

val playVersion = "2.5"
//val specs2Version = "3.9.5"

libraryDependencies ++= Seq(
  javaCore,
  jdbc ,
  cache ,
  ws   ,
//  specs2 % Test ,
//  "com.typesafe.play" %% "play-cache" % playVersion % Provided ,
//  "org.specs2" %% "specs2-core" % specs2Version % Test,
//  "com.typesafe.play" %% "play-specs2" % playVersion % Test,
  //swagger
  "io.swagger" %% "swagger-play2" % "1.5.3",
  "com.typesafe.play" %% "play-json" % "2.5.10",
  "org.webjars" %% "webjars-play" % "2.5.0-4",
  "org.webjars" % "swagger-ui" % "2.2.0",
  //mongod
  "org.mongodb" % "casbah_2.11" % "3.1.1",
  "org.slf4j" % "slf4j-simple" % "1.6.4",
  //
//  "com.rabbitmq" % "amqp-client" % "3.0.4",
//  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.19",
//  "org.apache.kafka" % "kafka_2.11" % "0.8.2.1"
//  "org.apache.kafka" % "kafka_2.11" % "0.8.2.1"
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.11-M2",
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "jquery" % "2.2.3",
  "org.webjars" % "bootstrap" % "3.3.6"
)

//unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"


//javacOptions ++= Seq( "-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-encoding", "UTF-8" )

//scalacOptions ++= Seq( "-deprecation", "-feature", "-unchecked",  "-Ylog-classpath" )
