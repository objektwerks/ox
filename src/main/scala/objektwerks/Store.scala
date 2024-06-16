package objektwerks

import com.typesafe.config.Config
import com.zaxxer.hikari.HikariDataSource

import javax.sql.DataSource

import scalikejdbc.*

final class Store(config: Config):
  private val dataSource: DataSource = {
    val ds = HikariDataSource()
    ds.setDataSourceClassName(config.getString("db.driver"))
    ds.addDataSourceProperty("url", config.getString("db.url"))
    ds.addDataSourceProperty("user", config.getString("db.user"))
    ds.addDataSourceProperty("password", config.getString("db.password"))
    ds
  }
  ConnectionPool.singleton( DataSourceConnectionPool(dataSource) )

  def addTodo(todo: Todo): Long =
    DB localTx { implicit session =>
      sql"insert into todo(task) values(${todo.task})".updateAndReturnGeneratedKey()
    }

  def updateTodo(todo: Todo): Int =
    DB localTx { implicit session =>
      sql"update todo set task = ${todo.task} where id = ${todo.id}".update()
    }

  def listTodos(): Seq[Todo] =
    DB readOnly { implicit session =>
      sql"select * from todo"
        .map(rs => Todo( rs.int("id"), rs.string("task") ) )
        .list()
    }