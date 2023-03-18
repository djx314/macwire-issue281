package macwire.test.service

import macwire.test.resource.{DBResource, PrintString}
import cats.effect.IO

class ServiceA(using resourceA: DBResource):
  def printlnResult: IO[Unit] = for
    u <- resourceA.getValue[IO]("xx")
    _ <- IO(println("resourceA value:" + u.toString))
  yield {}
end ServiceA

class ServiceB(using resourceB: DBResource, test: PrintString):
  def printlnResult: IO[Unit] = for
    u <- resourceB.getValue[IO]("xx")
    _ <- IO(println("resourceB value:" + u.toString))
  yield {}
end ServiceB

class ServiceC(using serviceA: ServiceA, serviceB: ServiceB, printString: PrintString):
  def printlnResult: IO[Unit] = for
    _ <- serviceA.printlnResult
    _ <- serviceB.printlnResult
    _ <- IO(println(printString.printlnResult))
  yield {}
end ServiceC
