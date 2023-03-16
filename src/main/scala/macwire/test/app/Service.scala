package macwire.test.app.service

import macwire.test.app.resource.{DBResource, PrintString}
import cats.effect.IO

case class ServiceA(resourceA: DBResource) {
  def printlnResult: IO[Unit] = for {
    u <- resourceA.getValue[IO]("xx")
    _ <- IO(println("resourceA value:" + u.toString))
  } yield {}
}

case class ServiceB(resourceB: DBResource, test: PrintString) {
  def printlnResult: IO[Unit] = for {
    u <- resourceB.getValue[IO]("xx")
    _ <- IO(println("resourceA value:" + u.toString))
  } yield {}
}

case class ServiceC(serviceA: ServiceA, serviceB: ServiceA, printString: PrintString) {
  def printlnResult: IO[Unit] = for {
    _ <- serviceA.printlnResult
    _ <- serviceB.printlnResult
    _ <- IO(println(printString.printlnResult))
  } yield {}
}
