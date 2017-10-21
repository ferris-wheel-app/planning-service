package ferris.planning.db

import slick.jdbc.MySQLProfile

object MySQLTables extends Tables {
  override val profile = MySQLProfile
}

trait MySQLTablesComponent extends TablesComponent {
  val tables = MySQLTables
}
