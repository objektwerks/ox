Ox
--
>Software Mill Ox feature tests using uJson, HikariCP, ScalikeJdbc, JoddMail, Postgresql and Scala 3.

Todo
----
1. Email

Install
-------
1. brew install postgresql@14

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
 [1] objektwerks.EmailApp
 [2] objektwerks.ParApp
 [3] objektwerks.SupervisedApp

Enter number:
```

Benchmark
---------
>See Performance class for details.
1. sbt jmh:run

Results
-------
>OpenJDK Runtime Environment Zulu22.30+13-CA (build 22.0.1+8), **Scala 3.5.0-RC1**, **Ox 0.2.1**, Apple M1
1. addTodo - 124.026 / 33.005 **
2. updateTodo - 60.618 / 30.721 **
3. listTodos - 735,343.828 / 25.852 **
>Total time: 616 s (10:16), 10 warmups, 10 iterations, average time in microseconds, completed **2024.6.16**

>** H2 in-memory database average time, not using Ox IO. See [ScalikeJdbc](https://github.com/objektwerks/scalikejdbc)

Slow Select
-----------
>Postgresql analysis for query ( select id, task from todo; ):
```
:> psql todo
psql (14.12 (Homebrew))
todo=# explain (analyze,buffers,verbose) select id, task from todo;
                                                QUERY PLAN
----------------------------------------------------------------------------------------------------------
 Seq Scan on public.todo  (cost=0.00..12.60 rows=260 width=282) (actual time=0.006..0.007 rows=1 loops=1)
   Output: id, task
   Buffers: shared hit=1
 Planning:
   Buffers: shared hit=58
 Planning Time: 0.464 ms
 Execution Time: 0.022 ms
(7 rows)
```

Postgresql
----------
1. config:
    1. on osx intel: /usr/local/var/postgres/postgresql.config : listen_addresses = ‘localhost’, port = 5432
    2. on osx m1: /opt/homebrew/var/postgres/postgresql.config : listen_addresses = ‘localhost’, port = 5432
2. run:
    1. brew services start postgresql@14
3. logs:
    1. on osx intel: /usr/local/var/log/postgres.log
    2. on m1: /opt/homebrew/var/log/postgres.log

Database
--------
>Example database url: postgresql://localhost:5432/todo?user=yourcomputername&password=yourpassword"
1. psql postgres
2. CREATE DATABASE house OWNER [your computer name];
3. GRANT ALL PRIVILEGES ON DATABASE todo TO [your computer name];
4. \l
5. \q
6. psql todo
7. \i ddl.sql
8. \q

DDL
---
>Alternatively run: psql -d todo -f ddl.sql
1. psql todo
2. \i ddl.sql
3. \q

Drop
----
1. psql postgres
2. drop database todo;
3. \q

Environment
-----------
```
export EMAIL_HOST="your.email.host.com"
export EMAIL_ADDRESS="your.email@com"
export EMAIL_PASSWORD="your.email.password"

export TODO_POSTGRESQL_DRIVER="org.postgresql.ds.PGSimpleDataSource"
export TODO_POSTGRESQL_URL="jdbc:postgresql://localhost:5432/todo"
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
