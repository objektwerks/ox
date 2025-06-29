package objektwerks

import ox.{forkUser, ExitCode, Ox, OxApp, supervised}
import ox.otel.context.PropagatingVirtualThreadFactory

/**
  * See: https://ox.softwaremill.com/latest/structured-concurrency/fork-join.html
  */
object SupervisedApp extends OxApp:
  override def settings: OxApp.Settings = OxApp.Settings.Default.copy(
    threadFactory = Some(PropagatingVirtualThreadFactory())
  )

  def run(args: Vector[String])(using Ox): ExitCode =
    supervised:
      List(
        forkUser( getJoke() ).join(),
        forkUser( getJoke() ).join(),
        forkUser( getJoke() ).join()
      )
      .foreach(println)

    ExitCode.Success