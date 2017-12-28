// factor out common settings into a sequence
lazy val commonSettings = Seq(
    organization := "",
    version      := "1.0",
    scalaVersion := "2.12.4"
)

// sbt-native-packager settings
//enablePlugins(JavaAppPackaging)

lazy val root = (project in file(".")).
    settings(commonSettings: _*).
    settings(
        // set the name of the project
        name := "Parser_Combinator_test_3",

        // Reflect of Ver2.10.0 requires to add libraryDependencies explicitly
        //libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,

        // add ScalaTest dependency
        //libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
        libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test",
        //libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "compile,test",

        // add Akka dependency
        //resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
        //libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.17",
        //libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.4.17",

        // add typesafe config dependencies
        //libraryDependencies += "com.typesafe" % "config" % "1.3.1",

        // add Logback, SLF4j dependencies
        //libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
        //libraryDependencies += "ch.qos.logback" % "logback-core" % "1.2.3",
        //libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25",

        // add nu.validator HTML parser dependencies
        // https://mvnrepository.com/artifact/nu.validator.htmlparser/htmlparser
        //libraryDependencies += "nu.validator.htmlparser" % "htmlparser" % "1.4",

        // add scala-parser-combinators dependencies
        // https://github.com/scala/scala-parser-combinators
        libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6",

        // add HZUtil dependency
        //libraryDependencies += "org.hirosezouen" %% "hzutil" % "2.2.0",
        // add HZActor dependency
        //libraryDependencies += "org.hirosezouen" %% "hzactor" % "1.1.0",

        // sbt-native-packager settings
        //executableScriptName := "Parser_Combinator_test_2",
        //batScriptExtraDefines += """set "APP_CLASSPATH=%APP_CLASSPATH%;%SSL_SERVER_HOME%\conf"""",
        //mappings in Universal := (mappings in Universal).value filterNot {
        //    case (_, name) => name.matches( """.*~|.*\.swp|.*\.swo""" )
        //},

        // Avoid sbt warning ([warn] This usage is deprecated and will be removed in sbt 1.0)
        // Current Sbt dose not allow overwrite stabele release created publicLocal task.
        isSnapshot := true,

        // fork new JVM when run and test and use JVM options
        //fork := true,

        // misc...
        //javaOptions += "-J-Xmx1024M",

        parallelExecution in Test := false,
        //logLevel := Level.Debug,
        scalacOptions += "-deprecation",
        scalacOptions += "-feature",
        scalacOptions += "-unchecked",
        scalacOptions += "-Xlint:_,-unused",
        scalacOptions += "-Ywarn-unused-import",
        scalacOptions += "-Xfatal-warnings"
    )

