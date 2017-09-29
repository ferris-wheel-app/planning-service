package com.planning.db

import slick.jdbc.H2Profile

object H2Tables extends Tables {
  override val profile = H2Profile
}

trait H2TablesComponent {
  val tables: Tables
}
