scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "org.typelevel"            %% "cats-effect" % "3.4.8",
  "com.softwaremill.macwire" %% "macros"             % "2.5.8" % Provided,
  "org.typelevel"            %% "cats-effect-laws"   % "3.4.8" % Test
)

addCompilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full)

scalafmtOnCompile := true

Global / onChangedBuildSource := ReloadOnSourceChanges
