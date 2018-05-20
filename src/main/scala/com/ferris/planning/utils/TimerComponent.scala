package com.ferris.planning.utils

import java.sql.Timestamp

trait TimerComponent {
  def timer: Timer

  trait Timer {
    def now: Timestamp
  }
}

trait DefaultTimerComponent extends TimerComponent {
  val timer: Timer = Timer

  private object Timer extends Timer {
    override def now: Timestamp = new Timestamp(System.currentTimeMillis())
  }
}
