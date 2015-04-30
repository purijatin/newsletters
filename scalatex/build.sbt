name := "newsletters"

version := "1.0"

scalatex.SbtPlugin.projectSettings

scalaVersion := "2.11.6"

lazy val generics = scalatex.ScalatexReadme(
  folder = "generics",
  url = "https://github.com/purijatin/newsletters/tree/master/scalatex",
  source = "Generics",
  targetFolder = "target/site"
)

