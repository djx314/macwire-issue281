# macwire-issue281

Issue Link: <https://github.com/softwaremill/macwire/issues/281>

1. [Injection code that need macwire](./src/main/scala/macwire/test/app/AppInjection.scala)
1. [Point Line](./src/main/scala/macwire/test/app/AppInjection.scala#L17)
1. [All code](./src/main/scala/macwire/test/app)

Run result
``` sbt
sbt:macwire-issue281> run
[info] running macwire.test.app.MainApp
resourceA value:5
resourceB value:2
macwire-issue281 executed successful.
[success] Total time: 1 s, completed 2023-3-17 3:41:23
```
