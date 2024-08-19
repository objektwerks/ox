package objektwerks

import com.typesafe.config.ConfigFactory

import ox.*
import ox.resilience.*

object EmailApp extends OxApp:
  def run(args: Vector[String])(using Ox, IO): ExitCode =
    val emailer = Emailer( EmailServerConfig( ConfigFactory.load("email.conf") ) )

    supervised:
      val either = retryEither( RetryConfig.immediate(2) )( Right( emailer.send( EmailConfig( ConfigFactory.load("email.conf") ) ) ) )
      assert( either.isRight )
    ExitCode.Success