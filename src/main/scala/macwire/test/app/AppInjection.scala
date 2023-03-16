package macwire.test.service

import cats.effect._
import com.softwaremill.macwire.wire
import macwire.test.resource.{DBResourceA, DBResourceB, Named, PrintStringResource}

class AppInjection {
  lazy val namedInstance: Named = Named("macwire-issue281")

  def serviceCResource[F[_]]: Resource[F, ServiceC] = wire[DBResourceA].resource[F].flatMap { dbA =>
    wire[DBResourceB].resource[F].flatMap { dbB =>
      wire[PrintStringResource].resource[F].map { implicit printlnString =>
        lazy val serviceA: ServiceA = ServiceA(resourceA = dbA) // By name injection.
        // Need more friendly.
        lazy val serviceB: ServiceB = ServiceB(resourceB = dbB, test = implicitly) // By name injection and by type injection.
        wire[ServiceC]
      }
    }
  }
}
