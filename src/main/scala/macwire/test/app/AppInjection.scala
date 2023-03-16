package macwire.test.app.service

import cats.effect._
import com.softwaremill.macwire.wire
import macwire.test.app.resource.{DBResourceA, DBResourceB, Named, PrintStringResource}

class AppInjection {
  lazy val namedInstance: Named = Named("macwire-issue281")

  def serviceCResource[F[_]]: Resource[F, ServiceC] = wire[DBResourceA].resource[F].flatMap { dbA =>
    wire[DBResourceB].resource[F].flatMap { dbB =>
      wire[PrintStringResource].resource[F].map { implicit printlnString =>
        lazy val serviceA: ServiceA = ServiceA(resourceA = dbA) // By name injection.
        // Need more friendly.
        lazy val serviceB: ServiceB = ServiceB(resourceB = dbB, test = implicitly) // By name injectioon and by type injection.
        wire[ServiceC]
      }
    }
  }
}
