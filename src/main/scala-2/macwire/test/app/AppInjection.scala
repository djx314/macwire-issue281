package macwire.test.service

import cats.effect._
import macwire.test.resource.{DBResourceA, DBResourceB, Named, PrintStringResource}

class AppInjection {
  implicit lazy val namedInstance: Named = Named("macwire-issue281")

  def serviceCResource[F[_]]: Resource[F, ServiceC] = (new DBResourceA).resource.flatMap { dbA =>
    (new DBResourceB).resource.flatMap { dbB =>
      (new PrintStringResource).resource.map { implicit printlnString =>
        lazy val serviceA: ServiceA = ServiceA(resourceA = dbA) // Injection by name.
        // Need more friendly.
        // `test = implicitly` is simply fetching variable `printlnString`.
        // I think we can do more things about both `implicit printlnString` and `test = implicitly`.
        lazy val serviceB: ServiceB = ServiceB(resourceB = dbB, test = implicitly) // Injection by name and injection by type.
        wire[ServiceC]
      }
    }
  }
}
