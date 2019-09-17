name := """fcscheduler"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.0"

libraryDependencies += guice

// JPA
libraryDependencies ++= Seq(
  javaJpa,
  "mysql" % "mysql-connector-java" % "8.0.17",
  evolutions,
  "org.hibernate" % "hibernate-entitymanager" % "5.4.4.Final",
)

// lombok
libraryDependencies += "org.projectlombok" % "lombok" % "1.18.10"
