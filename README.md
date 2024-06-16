Ox
--
>Software Mill Ox feature tests using uJson and Scala 3.

Build
-----
1. sbt clean compile

Test
----
1. sbt clean test

Run
---
1. sbt run
```
Multiple main classes detected. Select one to run:
 [1] objektwerks.runParApp
 [2] objektwerks.runSupervisedApp

Enter number:
```

Database ENV
------------
```
export TODO_POSTGRESQL_DRIVER="org.postgresql.ds.PGSimpleDataSource"
export TODO_POSTGRESQL_URL="postgresql://localhost:5432/todo"
export TODO_POSTGRESQL_USER="your.computer.name"
export TODO_POSTGRESQL_PASSWORD="your.password"
```

Resources
---------
* [Software Mill Ox Docs](https://ox.softwaremill.com/latest/)
* [Software Mill Ox Github](https://github.com/softwaremill/ox/)
* [Handling Errors in Direct Style](https://softwaremill.com/handling-errors-in-direct-style-scala/)
* [Limits of Loom's Performance](https://softwaremill.com/limits-of-looms-performance/)
* [Software Mill Ox](https://softwaremill.com/prototype-loom-based-concurrency-api-for-scala/)
* [Software Mill Go Channels in Scala](https://softwaremill.com/go-like-channels-using-project-loom-and-scala/)
* [Software Mill More On Go Channels in Scala](https://softwaremill.com/go-like-channels-in-scala-receive-send-and-default-clauses/)
* [Software Mill Ox: Supervision, Kafka and Java 21](https://softwaremill.com/supervision-kafka-and-java-21-whats-new-in-ox/)
* [Ox-Jdbc Gist](https://gist.github.com/lbialy/320b28dba6575cef3af8173e390abe54)
* [Java 21 Virtual Threads](https://www.youtube.com/watch?v=5E0LU85EnTI)
* [Java 21 Concurrency Guide](https://docs.oracle.com/en/java/javase/21/core/concurrency.html#GUID-59C16A2D-57CE-4C83-9D6F-91A48E01E3C6)
* [Virtual Threads: JEPS 425](openjdk.org/jeps/425)
* [Virtual Threads: JEPS 444](https://openjdk.org/jeps/444)
* [Scoped Values: JEPS 429](https://openjdk.org/jeps/429)
* [Scoped Values: JEPS 446](https://openjdk.org/jeps/446)
* [Structured Concurrency: JEPS 428](openjdk.org/jeps/428)
* [Structured Concurrency: JEPS 462](https://openjdk.org/jeps/462)
* [Loom](www.marcobehler.com/guides/java-project-loom)
* [Loom Blocking](https://softwaremill.com/what-is-blocking-in-loom/)
* [Virtual Threads Design](https://blogs.oracle.com/javamagazine/post/java-virtual-threads)
* [Ultimate Guide to Virtual Threads](https://blog.rockthejvm.com/ultimate-guide-to-java-virtual-threads/)
* [Unwrapping IO](https://www.youtube.com/watch?v=qR_Od7qbacs)
* [Software Mill Safe Direct-Style Scala](https://softwaremill.com/safe-direct-style-scala-ox-0-1-0-released/)
* [Software Mill Release 0.1.0 Thread](https://www.reddit.com/r/scala/comments/1cdfaki/safe_directstyle_scala_ox_010_released/)
* [Software Mill Direct Style Scala Stack Experiment](https://www.youtube.com/watch?v=C3j4AsFcxmM)
* [Software Mill IO Effect Tracking](https://softwaremill.com/io-effect-tracking-using-ox/)
* [Software Mill Ox & Tapir](https://softwaremill.com/websocket-chat-using-structured-concurrency-ox-and-tapir/)
