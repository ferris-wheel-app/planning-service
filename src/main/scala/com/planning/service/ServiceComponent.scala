package com.planning.service

import com.planning.repo.RepositoryComponent

trait ServiceComponent {
  val service: Service

  trait Service {

  }
}

trait DefaultServiceComponent extends ServiceComponent {
  this: RepositoryComponent =>

  class DefaultService extends Service {

  }
}
