package com.ferris.planning.utils

import java.sql.Timestamp

trait TimerComponent {
  def timer: Timer

  trait Timer {
    def timestampOfNow: Timestamp
    def now: Long
  }
}

trait DefaultTimerComponent extends TimerComponent {
  val timer: Timer = Timer

  private object Timer extends Timer {
    override def timestampOfNow: Timestamp = new Timestamp(System.currentTimeMillis())
    override def now: Long = System.currentTimeMillis()
  }
}
