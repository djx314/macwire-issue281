package macwire.test.service

import cats.Monad
import cats.effect.Resource
import macwire.test.resource.{DBResource, DBResourceA, DBResourceB, Named, PrintString, PrintStringResource}

class AppInjection:

  def applyM[F[_]]: Monad[Resource[F, *]] = Monad[Resource[F, *]]

  def serviceCResource[F[_]]: Resource[F, ServiceC] = for
    given Named       <- applyM.pure(Named("macwire-issue281"))
    dbA               <- (new DBResourceA).resource
    dbB               <- (new DBResourceB).resource
    given PrintString <- (new PrintStringResource).resource
    given ServiceA    <- applyM.pure(ServiceA(using resourceA = dbA))
    given ServiceB    <- applyM.pure(ServiceB(using resourceB = dbB))
  yield new ServiceC

end AppInjection
