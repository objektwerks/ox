package objektwerks

import com.typesafe.config.ConfigFactory

import ox.*
import ox.resilience.*

object EmailApp extends OxApp:
  def run(args: Vector[String])(using Ox): ExitCode =
    val config = ConfigFactory.load("email.conf")
    val emailer = Emailer( EmailServerConfig(config) )

    supervised:
      val either = retryEither( RetryConfig.immediate(2) )( Right( emailer.send( EmailConfig(config) ) ) )
      assert( either.isRight )

    ExitCode.Success