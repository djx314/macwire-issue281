package macwire.test.service

import cats.effect._
import com.softwaremill.macwire.wire
import macwire.test.resource.{DBResource, DBResourceA, DBResourceB, Named, PrintString, PrintStringResource}

class AppInjection {
  lazy val namedInstance: Named = Named("macwire-issue281")

<<<<<<< HEAD:src/main/scala-3/macwire/test/app/AppInjection.scala
  def serviceCResource[F[_]]: Resource[F, ServiceC] = wire[DBResourceA].resource.flatMap { dbA =>
    wire[DBResourceB].resource.flatMap { dbB =>
      wire[PrintStringResource].resource.map { implicit printlnString =>
        lazy val serviceA: ServiceA = ServiceA(resourceA = dbA) // Injection by name.
        // Need more friendly.
        // `test = implicitly` is simply fetching variable `printlnString`.
        // I think we can do more things about both `implicit printlnString` and `test = implicitly`.
        lazy val serviceB: ServiceB = ServiceB(resourceB = dbB, test = implicitly) // Injection by name and injection by type.
=======
  private class ServiceBImpl(test: PrintString) {
    def build(resourceB: DBResource): ServiceB = ServiceB(resourceB = resourceB, test = test)
  }

  def serviceCResource[F[_]]: Resource[F, ServiceC] = wire[DBResourceA].resource.flatMap(dbA =>
    wire[DBResourceB].resource.flatMap(dbB =>
      wire[PrintStringResource].resource.map { printlnString =>
        lazy val serviceA: ServiceA         = ServiceA(resourceA = dbA)           // Injection by name.
        lazy val serviceBImpl: ServiceBImpl = wire[ServiceBImpl]
        lazy val serviceB: ServiceB         = serviceBImpl.build(resourceB = dbB) // Injection by name and injection by type.
>>>>>>> fix-issue:src/main/scala/macwire/test/app/AppInjection.scala
        wire[ServiceC]
      }
    )
  )
}
