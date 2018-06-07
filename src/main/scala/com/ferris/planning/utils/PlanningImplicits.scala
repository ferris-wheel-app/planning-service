package com.ferris.planning.utils

import java.time.{LocalDateTime, ZoneId}

object PlanningImplicits {
  implicit class LocalDateTimeOps(time: LocalDateTime) {
    def toLong: Long = {
      time.atZone(ZoneId.systemDefault()).toInstant.toEpochMilli
    }
  }
}
