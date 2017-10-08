package ferris.planning.db

import slick.jdbc.H2Profile
import slick.jdbc.MySQLProfile

object H2Tables extends Tables {
  override val profile = MySQLProfile
}

trait H2TablesComponent extends TablesComponent {
  val tables = H2Tables
}
