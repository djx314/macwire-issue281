scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "org.typelevel"            %% "cats-effect-kernel" % "3.4.8",
  "com.softwaremill.macwire" %% "macros"             % "2.5.8" % Provided,
  "org.typelevel"            %% "cats-effect-laws"   % "3.4.8" % Test
)

Global / onChangedBuildSource := ReloadOnSourceChanges
