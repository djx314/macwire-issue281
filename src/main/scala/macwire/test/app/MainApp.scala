package macwire.test.app

import cats.effect._
import macwire.test.service.AppInjection

object MainApp extends IOApp {
  def run(args: List[String]): IO[ExitCode] = (new AppInjection).serviceCResource[IO].use { serviceC =>
    for (_ <- serviceC.printlnResult) yield ExitCode.Success
  }
}
