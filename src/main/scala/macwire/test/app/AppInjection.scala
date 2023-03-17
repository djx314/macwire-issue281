package macwire.test.service

import cats.effect._
import com.softwaremill.macwire.wire
import macwire.test.resource.{DBResource, DBResourceA, DBResourceB, Named, PrintString, PrintStringResource}

class AppInjection {
  lazy val namedInstance: Named = Named("macwire-issue281")

  private class ServiceBImpl(test: PrintString) {
    def build(resourceB: DBResource): ServiceB = ServiceB(resourceB = resourceB, test = test)
  }

  def serviceCResource[F[_]]: Resource[F, ServiceC] = wire[DBResourceA].resource.flatMap(dbA =>
    wire[DBResourceB].resource.flatMap(dbB =>
      wire[PrintStringResource].resource.map { printlnString =>
        lazy val serviceA: ServiceA         = ServiceA(resourceA = dbA)           // Injection by name.
        lazy val serviceBImpl: ServiceBImpl = wire[ServiceBImpl]
        lazy val serviceB: ServiceB         = serviceBImpl.build(resourceB = dbB) // Injection by name and injection by type.
        wire[ServiceC]
      }
    )
  )
}
