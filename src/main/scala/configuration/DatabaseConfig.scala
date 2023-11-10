package configuration

import slick.jdbc.JdbcProfile

object DatabaseConfig {
  val profile: JdbcProfile = slick.jdbc.MySQLProfile
  import profile.api._

  val db: Database = Database.forConfig("myPlay")
}