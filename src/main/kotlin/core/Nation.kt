package core

import io.reactivex.subjects.PublishSubject
import math.Vector2i
import network.Client
import network.Server

data class EventOnAddHexToNation(val nation: Nation, val hex: Hex)
data class EventOnRemoveHexFromNation(val nation: Nation, val hex: Hex)

class Nation(val name: String) {
  val clients = mutableListOf<Client>()

  companion object {
    val onAddHexToNation = PublishSubject.create<EventOnAddHexToNation>()
    val onRemoveHexFromNation = PublishSubject.create<EventOnRemoveHexFromNation>()
  }

  init {
    Server.onClientDisconnected.subscribe {
      clients.remove(it.client)
    }
  }

  fun addHex(position: Vector2i) {
    val hex = State.getHexByPosition(position) ?: return
    return addHex(hex)
  }

  fun addHex(hex: Hex) {
    hex.nationNames.add(name)
    onAddHexToNation.onNext(EventOnAddHexToNation(this, hex))
  }

  fun removeHex(position: Vector2i) {
    val hex = State.getHexByPosition(position) ?: return
    return removeHex(hex)
  }

  fun removeHex(hex: Hex) {
    hex.nationNames.remove(name)
    onRemoveHexFromNation.onNext(EventOnRemoveHexFromNation(this, hex))
  }
}