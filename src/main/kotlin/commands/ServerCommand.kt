package commands

import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import logError
import network.Server
import java.lang.Exception

@UnstableDefault
fun dispatchServerCommand(clientId: String, serverCommand: ServerCommand): Boolean {
  val client = Server.getClientById(clientId) ?: return false
  val type = serverCommand::class.java.toString().split("$").last()
  return try {
    val json = when (serverCommand) {
      is ServerCommand.InvalidPlayerInfo -> Json.stringify(ServerCommand.InvalidPlayerInfo.serializer(), serverCommand)
      is ServerCommand.FullState -> serverCommand.stateJson
    }
    client.send("$type|$json")
    true
  } catch (e: Exception) {
    logError("Failed to dispatch Server Command")
    false
  }
}

@UnstableDefault
fun broadcastServerCommand(serverCommand: ServerCommand): Boolean {
  val dispatches = Server.getClientIds().map { dispatchServerCommand(it, serverCommand) }
  return dispatches.all { it }
}

sealed class ServerCommand {
  @Serializable class InvalidPlayerInfo(val error: String) : ServerCommand()
  @Serializable class FullState(val stateJson: String) : ServerCommand()
}