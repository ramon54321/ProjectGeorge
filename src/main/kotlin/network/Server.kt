package network

import PORT
import io.reactivex.subjects.PublishSubject
import logInfo
import java.net.ServerSocket
import kotlin.concurrent.thread

data class EventOnClientConnected(val client: Client)
data class EventOnClientDisconnected(val client: Client)
data class EventOnClientMessage(val client: Client, val message: String)

object Server {
  private val server = ServerSocket(PORT)

  val onClientConnected = PublishSubject.create<EventOnClientConnected>()
  val onClientDisconnected = PublishSubject.create<EventOnClientDisconnected>()
  val onClientMessage = PublishSubject.create<EventOnClientMessage>()

  private val clients = mutableMapOf<String, Client?>()
  private var isRunning = true

  init {
    thread {
      logInfo("Running server on $PORT")

      while (isRunning) {
        val clientSocket = server.accept()

        thread(name = "network.Client Manager") {
          Client(clientSocket, onOpen = {
            clients[it.uuid] = it
            onClientConnected.onNext(EventOnClientConnected(it))
            logInfo("network.Client added -> $clients")
          }, onClose = {
            clients.remove(it.uuid)
            onClientDisconnected.onNext(EventOnClientDisconnected(it))
            logInfo("network.Client removed -> $clients")
          })
        }
      }

      logInfo("network.Server stopped")
    }
    logInfo("network.Server listener dispatched")
  }

  fun getClientById(clientId: String): Client? {
    return clients[clientId]
  }
}

