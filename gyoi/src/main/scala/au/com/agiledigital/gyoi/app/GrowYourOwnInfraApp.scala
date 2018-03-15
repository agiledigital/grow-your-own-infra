package au.com.agiledigital.gyoi.app

import java.util.ServiceLoader

import au.com.agiledigital.gyoi.plugin.BringYourOwnInfraCommandPlugin
import au.com.agiledigital.gyoi.command.noop.NoOpCommand
import au.com.agiledigital.gyoi.version.BuildInfo
import cats.data.NonEmptyList
import com.monovore.decline._

import scala.collection.immutable.Seq
import scala.collection.JavaConverters._

/**
  * A tool to generate CI/CD pipelines from a project definition.
  */
object GrowYourOwnInfraApp
    extends CommandApp(
      name = BuildInfo.name,
      version = BuildInfo.version,
      header = "Bootstraps a development environment using Toolform and Minishift.",
      main = CliParserConfiguration.commandLineOptions.map {
        case Left(errors) =>
          System.err.println("Failed due to the following errors:")
          errors.toList.map({ _.message }).foreach(System.err.println)
          System.exit(1)
        case Right(output) =>
          println(output)
          System.exit(0)
      }
    )

/**
  * Builds command line parser options that include all available
  * toolform commands.  The options are in monovore/decline style, accessed
  * via the [[Opts]] trait and runnable by wrapping in a [[Command]] or
  * [[CommandApp]].
  */
object CliParserConfiguration {

  def plugins: List[BringYourOwnInfraCommandPlugin] = List(
    new NoOpCommand()
  )

  /**
    * Command line parser options.
    * @return Opts style command line parser options for use in a decline
    *         parser/app runner.
    */
  def commandLineOptions: Opts[Either[NonEmptyList[GrowYourOwnInfraError], String]] =
    plugins.map(_.command).reduce(_ orElse _)
}

/**
  * A simple error type for the toolform CLI app.
  *
  * @param message The error detail message.
  */
final case class GrowYourOwnInfraError(message: String)
