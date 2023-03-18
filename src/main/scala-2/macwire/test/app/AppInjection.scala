package macwire.test.service

import cats.effect._
import macwire.test.resource.{DBResourceA, DBResourceB, Named, PrintStringResource}

class AppInjection {
  implicit lazy val namedInstance: Named = Named("macwire-issue281")

  def serviceCResource[F[_]]: Resource[F, ServiceC] = (new DBResourceA).resource.flatMap { dbA =>
    (new DBResourceB).resource.flatMap { dbB =>
      (new PrintStringResource).resource.map { implicit printlnString =>
        implicit lazy val serviceA: ServiceA = new ServiceA()(resourceA = dbA)
        implicit lazy val serviceB: ServiceB = new ServiceB()(resourceB = dbB, test = implicitly)
        new ServiceC
      }
    }
  }
}
