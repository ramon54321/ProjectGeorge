package network

import core.Nation
import logInfo
import java.net.Socket
import java.util.*

class Client(
  private val socket: Socket,
  private val onOpen: ((it: Client) -> Unit)? = null,
  private val onClose: ((it: Client) -> Unit)? = null
) {
  private val input = Scanner(socket.getInputStream())
  private val output = socket.getOutputStream()

  private var isRunning = true

  val uuid = UUID.randomUUID().toString()
  var nation: Nation? = null

  init {
    logInfo("New client -> ${socket.inetAddress.hostAddress}")
    onOpen?.invoke(this)

    while (isRunning) {
      try {
        val message = input.nextLine()
        logInfo("Message: $message")
        Server.onClientMessage.onNext(EventOnClientMessage(this, message))
      } catch (e: Exception) {
        close()
      }
    }

    logInfo("Client stopped")
  }

  fun send(message: String) {
    output.write(message.toByteArray(), 0, message.length)
  }

  private fun close() {
    isRunning = false
    socket.close()
    onClose?.invoke(this)
  }
}