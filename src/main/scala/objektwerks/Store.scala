package objektwerks

import com.typesafe.config.Config
import com.zaxxer.hikari.HikariDataSource

import javax.sql.DataSource

import ox.IO

import scalikejdbc.*

object Store:
  def apply(config: Config): Store =
    val dataSource: DataSource =
      val ds = HikariDataSource()
      ds.setDataSourceClassName(config.getString("db.driver"))
      ds.addDataSourceProperty("url", config.getString("db.url"))
      ds.addDataSourceProperty("user", config.getString("db.user"))
      ds.addDataSourceProperty("password", config.getString("db.password"))
      ds
    new Store(dataSource)

private final class Store(dataSource: DataSource):
  ConnectionPool.singleton( DataSourceConnectionPool(dataSource) )

  def addTodo(todo: Todo)(using IO): Long =
    DB localTx { implicit session =>
      sql"insert into todo(task) values(${todo.task})"
      .updateAndReturnGeneratedKey()
    }

  def updateTodo(todo: Todo)(using IO): Int =
    DB localTx { implicit session =>
      sql"update todo set task = ${todo.task} where id = ${todo.id}"
      .update()
    }

  def listTodos()(using IO): Seq[Todo] =
    DB readOnly { implicit session =>
      sql"select id, task from todo"
        .map(rs => Todo( rs.long("id"), rs.string("task") ) )
        .list()
    }