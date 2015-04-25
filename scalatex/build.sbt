name := "newsletters"

version := "1.0"

scalatex.SbtPlugin.projectSettings

scalaVersion := "2.11.6"

lazy val generics = scalatex.ScalatexReadme(
  folder = "generics",
  url = "https://github.com/lihaoyi/scalatex/tree/master",
  source = "Generics",
  targetFolder = "target/site"
)

