package macwire.test.app.service

import cats.effect._

object MainApp extends IOApp {
  def run(args: List[String]): IO[ExitCode] = (new AppInjection).serviceCResource[IO].use { serviceC =>
    for (_ <- serviceC.printlnResult) yield ExitCode.Success
  }
}
