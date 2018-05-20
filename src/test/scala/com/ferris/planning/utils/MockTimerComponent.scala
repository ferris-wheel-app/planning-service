package com.ferris.planning.utils

import java.sql.Timestamp

import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

trait MockTimerComponent extends TimerComponent with MockitoSugar {
  override val timer: Timer = mock[Timer]
  when(timer.now).thenReturn(new Timestamp(System.currentTimeMillis()))
}
