package au.com.agiledigital.gyoi.plugin

import au.com.agiledigital.gyoi.app.GrowYourOwnInfraError
import cats.data.NonEmptyList
import com.monovore.decline._

/**
  * Extension point to add new commands to toolform.
  * Uses Java SPI - See [[java.util.ServiceLoader]] for details.
  * Implement the trait and register the new implementation in
  * META-INF/services/au.com.agiledigital.toolform.plugin.ToolFormCommandPlugin
  * on the runtime classpath.
  */
trait BringYourOwnInfraCommandPlugin {
  def command: Opts[Either[NonEmptyList[GrowYourOwnInfraError], String]]
}
