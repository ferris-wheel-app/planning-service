package com.ferris.planning.db

import com.ferris.planning.table.Tables
import slick.jdbc.H2Profile

object H2Tables extends Tables {
  override val profile = H2Profile
}

trait H2TablesComponent extends TablesComponent {

  override val tables = H2Tables
}
