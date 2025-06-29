package objektwerks

import ox.{ExitCode, Ox, OxApp, par, supervised}
import ox.otel.context.PropagatingVirtualThreadFactory

object OtelApp extends OxApp:
  override def settings: OxApp.Settings = OxApp.Settings.Default.copy(
    threadFactory = Some(PropagatingVirtualThreadFactory())
  )

  def run(args: Vector[String])(using Ox): ExitCode =
    supervised:
      par(
        getJoke(),
        getJoke(),
        getJoke()
      )
      .toList
      .foreach(println)

    ExitCode.Success