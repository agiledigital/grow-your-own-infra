package au.com.agiledigital.gyoi.command.noop

import java.nio.file.Path

import au.com.agiledigital.gyoi.app.GrowYourOwnInfraError
import au.com.agiledigital.gyoi.plugin.BringYourOwnInfraCommandPlugin
import cats.data.NonEmptyList
import com.monovore.decline._

/**
  * Prints a summary of a project definition.
  */
final class NoOpCommand extends BringYourOwnInfraCommandPlugin {

  override val command: Opts[Either[NonEmptyList[GrowYourOwnInfraError], String]] =
    Opts
      .subcommand("noop", "Do nothing of value to test the build works") {
        Opts.option[String]("input", short = "m", metavar = "message", help = "A message to print to the screen")
      }
      .map(execute)

  def execute(message: String): Either[NonEmptyList[GrowYourOwnInfraError], String] =
    if (message == "error") {
      Left(NonEmptyList.of(GrowYourOwnInfraError(s"Oops! An error occurred.")))
    } else {
      for {
        message <- printMessage()
      } yield message
    }

  private def printMessage(): Either[NonEmptyList[GrowYourOwnInfraError], String] =
    Right("Nothing happened successfully!")
}
