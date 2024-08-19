package objektwerks

import com.typesafe.config.ConfigFactory

import ox.*
import ox.resilience.*

object EmailApp extends OxApp:
  def run(args: Vector[String])(using Ox, IO): ExitCode =
    val config = EmailConfig( ConfigFactory.load("email.conf") )
    val emailer = Emailer(config)

    supervised:
      val either = retryEither( RetryConfig.immediate(2) )( Right( emailer.send(config.recipients, config.subject, config.message) ) )
      assert( either.isRight )
    ExitCode.Success