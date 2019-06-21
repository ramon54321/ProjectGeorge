package commands

import io.reactivex.subjects.PublishSubject

object Commands {
  val onCommand = PublishSubject.create<ClientCommand>()
}