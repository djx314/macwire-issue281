package macwire.test.resource

import cats.Applicative
import cats.effect.Resource

case class Named(appName: String)

class NamedResource(appName: String):
  def resource[F[_]]: Resource[F, Named] = Applicative[Resource[F, *]].pure(Named(appName))
end NamedResource

abstract class DBResource:
  def dbName: String
  def initMap: Map[String, Int]
  def getValue[F[_]: Applicative](name: String): F[Int] = Applicative[F].pure(initMap(name))
end DBResource

class DBResourceA(using named: Named):
  def resource[F[_]]: Resource[F, DBResource] = Applicative[Resource[F, *]].pure(new DBResource:
    override def dbName: String            = "dbName" + named.appName
    override def initMap: Map[String, Int] = Map(("a", 2), ("b", 6), ("d", 5), ("e", 2), ("xx", 5))
  )
  end resource
end DBResourceA

class DBResourceB(using named: Named):
  def resource[F[_]]: Resource[F, DBResource] = Applicative[Resource[F, *]].pure(new DBResource:
    override def dbName: String            = "dbName" + named.appName
    override def initMap: Map[String, Int] = Map(("x0", 0), ("x1", 1), ("x2", 2), ("x3", 3), ("xx", 2))
  )
  end resource
end DBResourceB

class PrintString(appName: String):
  def printlnResult: String = appName + " executed successful."
end PrintString

class PrintStringResource(using named: Named):

  def resource[F[_]]: Resource[F, PrintString] = Applicative[Resource[F, *]].pure(new PrintString(named.appName))
end PrintStringResource
