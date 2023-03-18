package macwire.test.service

import cats.effect._
import macwire.test.resource.{DBResource, DBResourceA, DBResourceB, Named, PrintString, PrintStringResource}
import cats.Applicative

class AppInjection {

  def serviceCResource[F[_]]: Resource[F, ServiceC] = for
    given Named       <- Applicative[Resource[F, *]].pure(Named("macwire-issue281"))
    dbA               <- (new DBResourceA).resource
    dbB               <- (new DBResourceB).resource
    given PrintString <- (new PrintStringResource).resource
    given ServiceA = ServiceA(using resourceA = dbA)
    given ServiceB = ServiceB(using resourceB = dbB)
  yield new ServiceC

}
