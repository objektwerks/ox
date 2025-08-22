package objektwerks

import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging

import jodd.mail.{Email, MailServer, SmtpServer}

import scala.language.postfixOps
import scala.util.Using

final case class EmailServerConfig(config: Config):
  val host = config.getString("email.host")
  val sender = config.getString("email.sender")
  val password = config.getString("email.password")

final case class EmailConfig(config: Config):
  val recipients = List( config.getString("email.sender") )
  val subject = config.getString("email.subject")
  val message = config.getString("email.message")

final class Emailer(config: EmailServerConfig) extends LazyLogging:
  private val smtpServer: SmtpServer = MailServer.create
    .host(config.host)
    .ssl(true)
    .auth(config.sender, config.password)
    .buildSmtpMailServer

  private def sendEmail(recipients: List[String],
                        subject: String,
                        message: String): Either[Throwable, Unit] =
    Using( smtpServer.createSession ) { session =>
      val email = Email.create
        .from(config.sender)
        .subject(subject)
        .textMessage(message, "UTF-8")
        .cc(config.sender)
      recipients.foreach( recipient => email.to(recipient) )
      session.open
      val messageId = session.sendMail(email)
      logger.info("*** Emailer subject: {} to: {} message id: {}", subject, recipients.mkString, messageId)
    }.recover { error =>
      logger.error("*** Emailer subject: {} to: {} failed: {}",  subject, recipients.mkString, error.getMessage)
      throw error
    }.toEither

  def send(config: EmailConfig): Either[Throwable, Unit] = sendEmail(config.recipients, config.subject, config.message)