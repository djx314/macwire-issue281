package macwire.test.service

import cats.Monad
import cats.effect.Resource
import macwire.test.resource.{DBResourceA, DBResourceB, Named, NamedResource, PrintStringResource}

class AppInjection {

  def serviceCResource[F[_]: Monad]: Resource[F, ServiceC] = new NamedResource("macwire-issue281").resource.flatMap(implicit proNamed =>
    (new DBResourceA).resource.flatMap(dbA =>
      (new DBResourceB).resource.flatMap(dbB =>
        (new PrintStringResource).resource.map { implicit printlnString =>
          implicit lazy val serviceA: ServiceA = new ServiceA()(resourceA = dbA)
          implicit lazy val serviceB: ServiceB = new ServiceB()(resourceB = dbB, test = implicitly)
          new ServiceC
        }
      )
    )
  )

}
