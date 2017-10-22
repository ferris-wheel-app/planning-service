package com.ferris.planning.service.exceptions

object Exceptions {

  sealed abstract class PlanningServiceException(message: String) extends Exception(message)

  case class MessageNotFoundException(message: String = "message not found") extends PlanningServiceException(message)
}
