package macwire.test.resource

import cats.Applicative
import cats.effect.kernel.Resource

case class Named(appName: String)

abstract class DBResource {
  def dbName: String
  def initMap: Map[String, Int]
  def getValue[F[_]: Applicative](name: String): F[Int] = Applicative[F].pure(initMap(name))
}

class DBResourceA(implicit named: Named) {
  def resource[F[_]]: Resource[F, DBResource] = Applicative[Resource[F, *]].pure(new DBResource {
    override def dbName: String            = "dbName" + named.appName
    override def initMap: Map[String, Int] = Map(("a", 2), ("b", 6), ("d", 5), ("e", 2), ("xx", 5))
  })
}

class DBResourceB(implicit named: Named) {
  def resource[F[_]]: Resource[F, DBResource] = Applicative[Resource[F, *]].pure(new DBResource {
    override def dbName: String            = "dbName" + named.appName
    override def initMap: Map[String, Int] = Map(("x0", 0), ("x1", 1), ("x2", 2), ("x3", 3), ("xx", 2))
  })
}

class PrintString(appName: String) {
  def printlnResult: String = appName + " executed successful."
}

class PrintStringResource(implicit named: Named) {
  def resource[F[_]]: Resource[F, PrintString] = Applicative[Resource[F, *]].pure(new PrintString(named.appName))
}
