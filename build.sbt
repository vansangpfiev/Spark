name := "Indexed"

version := "1.0"

scalaVersion := "2.11.8"
val sparkVersion = "2.1.0"
//retrieveManaged := true

//resolvers += "SBT Pack Repository" at "http://repo1.maven.org/maven2/org/xerial/sbt/"

// Automatically find def main(args:Array[String]) methods from classpath
//packAutoSettings

mainClass in (Compile,run) := Some("com.hadas.index.SparkMain")

//libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "1.2.1"

libraryDependencies ++= Seq(
  "org.mongodb.scala" %% "mongo-scala-driver" % "1.2.1",
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "provided"
)



