scalaVersion := scalaV.v213

libraryDependencies ++= libScalax.`scala-collection-compat`.value
libraryDependencies ++= libScalax.`kind-projector`.value
libraryDependencies ++= libScalax.`cats-effect`.value

scalafmtOnCompile := true

Global / onChangedBuildSource := ReloadOnSourceChanges
