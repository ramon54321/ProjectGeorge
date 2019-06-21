import io.reactivex.subjects.PublishSubject
import kotlinx.serialization.UnstableDefault
import network.Client
import java.util.*
import kotlin.concurrent.thread

data class EventOnGameStart(val client: Client)
data class EventOnGameTick(val date: Date)
data class EventOnGameEnd(val client: Client)

@UnstableDefault
object Ticker {
  val onStart = PublishSubject.create<EventOnGameStart>()
  val onTick = PublishSubject.create<EventOnGameTick>()
  val onEnd = PublishSubject.create<EventOnGameEnd>()

  init {
    thread {
      while(true) {
        onTick.onNext(EventOnGameTick(Date()))
        Thread.sleep(1000L / TICK_RATE)
      }
    }
  }
}