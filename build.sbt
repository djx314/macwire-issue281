scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect-kernel" % "3.4.8",
  "org.typelevel" %% "cats-effect-laws"   % "3.4.8" % Test)