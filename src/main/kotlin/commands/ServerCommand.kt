package commands

import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import network.Server
import java.lang.Exception

@UnstableDefault
fun dispatchServerCommand(clientId: String, serverCommand: ServerCommand): Boolean {
  val client = Server.getClientById(clientId) ?: return false
  val type = serverCommand::class.java.toString().split("$").last()
  try {
    val json = when (type) {
      "InvalidPlayerInfo" -> Json.stringify(ServerCommand.InvalidPlayerInfo.serializer(), serverCommand as ServerCommand.InvalidPlayerInfo)
      else -> return false
    }
    client.send(json)
    return true
  } catch (e: Exception) {
    return false
  }
}

sealed class ServerCommand {
  @Serializable class InvalidPlayerInfo(val error: String) : ServerCommand()
}