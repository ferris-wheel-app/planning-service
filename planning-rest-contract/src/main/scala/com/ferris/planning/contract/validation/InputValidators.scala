package com.ferris.planning.contract.validation

import com.ferris.microservice.exceptions.ApiExceptions.{InvalidFieldException, InvalidFieldPayload}

object InputValidators {

  def requireField(cond: Boolean, name: String): Unit = {
    val displayedName: String = camelCaseToSpaced(name)
    if (!cond) throw InvalidFieldException("InvalidField", s"Invalid $displayedName", Some(InvalidFieldPayload(name)))
  }
  def requireField(cond: Boolean, name: String, msg: String): Unit = {
    if (!cond) throw InvalidFieldException("InvalidField", msg, Some(InvalidFieldPayload(name)))
  }

  private def camelCaseToSpaced(s: String): String = {
    require(s.length > 0)
    val tail = s.substring(1).foldRight(List.empty[Char]){ (char, arr) =>
      if (char.isUpper) ' ' :: char :: arr
      else char :: arr
    }
    s.charAt(0).toUpper :: tail mkString ""
  }
}
