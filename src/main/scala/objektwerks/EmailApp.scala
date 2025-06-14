package objektwerks

import com.typesafe.config.ConfigFactory

import ox.{ExitCode, Ox, OxApp, supervised}
import ox.resilience.retryEither
import ox.scheduling.Schedule

object EmailApp extends OxApp:
  def run(args: Vector[String])(using Ox): ExitCode =
    val config = ConfigFactory.load("email.conf")
    val emailer = Emailer( EmailServerConfig(config) )

    supervised:
      val either = retryEither( Schedule.immediate )( Right( emailer.send( EmailConfig(config) ) ) )
      assert( either.isRight )

    ExitCode.Success