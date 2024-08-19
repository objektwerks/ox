package objektwerks

import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging

import jodd.mail.{Email, MailServer, SmtpServer}

import scala.language.postfixOps
import scala.util.Using

final class Emailer(config: Config) extends LazyLogging:
  private val host = config.getString("email.host")
  private val sender = config.getString("email.sender")
  private val password = config.getString("email.password")

  private val smtpServer: SmtpServer = MailServer.create
    .host(host)
    .ssl(true)
    .auth(sender, password)
    .buildSmtpMailServer

  private def sendEmail(recipients: List[String],
                        subject: String,
                        message: String): Unit =
    Using( smtpServer.createSession ) { session =>
      val email = Email.create
        .from(sender)
        .subject(subject)
        .textMessage(message, "UTF-8")
        .cc(sender)
      recipients.foreach( recipient => email.to(recipient) )
      session.open
      val messageId = session.sendMail(email)
      logger.info("*** Emailer subject: {} to: {} message id: {}", subject, recipients.mkString, messageId)
    }.recover { error =>
      logger.error("*** Emailer subject: {} to: {} failed: {}",  subject, recipients.mkString, error.getMessage)
      throw error
    }

  def send(recipients: List[String],
           subject: String,
           message: String): Unit = sendEmail(recipients, subject, message)
