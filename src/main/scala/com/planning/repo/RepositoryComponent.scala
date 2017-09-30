package com.planning.repo

import com.planning.db.TablesComponent

trait RepositoryComponent {
  val repo: Repository

  trait Repository {

  }
}

trait H2RepositoryComponent extends RepositoryComponent {
  this: TablesComponent =>

  class H2Repository extends Repository {

  }
}

