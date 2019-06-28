package core

import TICKER_DELAY
import TICK_RATE
import io.reactivex.subjects.PublishSubject
import java.util.*
import kotlin.concurrent.thread

class EventOnGameStart
data class EventOnGameTick(val count: Long, val date: Date)
class EventOnGameEnd

object Ticker {
  val onEarlyStart = PublishSubject.create<EventOnGameStart>()
  val onStart = PublishSubject.create<EventOnGameStart>()
  val onTick = PublishSubject.create<EventOnGameTick>()
  val onEnd = PublishSubject.create<EventOnGameEnd>()

  init {
    thread(name = "Ticker") {
      Thread.sleep(TICKER_DELAY)
      onEarlyStart.onNext(EventOnGameStart())
      onStart.onNext(EventOnGameStart())
      var count = 0L
      while(true) {
        onTick.onNext(EventOnGameTick(count, Date()))
        count++
        Thread.sleep(1000L / TICK_RATE)
      }
      onEnd.onNext(EventOnGameEnd())
    }
  }
}