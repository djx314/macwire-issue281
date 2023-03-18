package macwire.test.service

import cats.Monad
import cats.effect.Resource
import macwire.test.resource.{DBResource, DBResourceA, DBResourceB, Named, NamedResource, PrintString, PrintStringResource}

class AppInjection:

  def serviceCResource[F[_]]: Resource[F, ServiceC] = for
    given Named       <- NamedResource("macwire-issue281").resource
    dbA               <- DBResourceA().resource
    dbB               <- DBResourceB().resource
    given PrintString <- PrintStringResource().resource
    given ServiceA = ServiceA(using resourceA = dbA)
    given ServiceB = ServiceB(using resourceB = dbB)
  yield ServiceC()

end AppInjection
