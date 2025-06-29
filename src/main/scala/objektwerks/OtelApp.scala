package objektwerks

import ox.{ExitCode, Ox, OxApp, supervised}
import ox.otel.context.PropagatingVirtualThreadFactory
import ox.scheduling.{repeat, Schedule}

import scala.concurrent.duration.DurationInt

object OtelApp extends OxApp:
  override def settings = OxApp.Settings.Default.copy(
    threadFactory = Some( PropagatingVirtualThreadFactory() )
  )

  def run(args: Vector[String])(using Ox): ExitCode =
    supervised:
      repeat( Schedule.fixedInterval(2.seconds) )( println( getJoke() ) )

    ExitCode.Success