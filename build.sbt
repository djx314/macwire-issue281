scalaVersion := scalaV.v211

libraryDependencies ++= libScalax.`scala-collection-compat`.value
libraryDependencies ++= libScalax.`kind-projector`.value
libraryDependencies ++= libScalax.`cats-effect`.value

scalafmtOnCompile := true

scalacOptions ++= {
  if (scalaBinaryVersion.value == "3") Seq("-Ykind-projector") else Seq.empty
}

crossScalaVersions := Seq(scalaV.v211, scalaV.v212, scalaV.v213, scalaV.v3)

Global / onChangedBuildSource := ReloadOnSourceChanges
