package objektwerks

import com.typesafe.config.ConfigFactory

import ox.*
import ox.resilience.*

object EmailApp extends OxApp:
  def run(args: Vector[String])(using Ox, IO): ExitCode =
    val config = ConfigFactory.load("email.conf")
    val host = config.getString("email.host")
    val sender = config.getString("email.sender")
    val password = config.getString("email.password")
    val recipients = List( config.getString("email.sender") )
    val subject = config.getString("email.subject")
    val message = config.getString("email.message")
    val emailer = Emailer(host, sender, password)

    supervised:
      val either = retryEither( RetryConfig.immediate(2) )( Right( emailer.send(recipients, subject, message) ) )
      assert( either.isRight )
    ExitCode.Success