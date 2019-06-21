package commands

import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import java.lang.Exception

@UnstableDefault
fun parseClientCommand(clientId: String, message: String): ClientCommand {
  val messageParts = message.split("|")
  val type = messageParts.first()
  val json = messageParts.last()
  try {
    val serializer = when (type) {
      "PlayerInfo" -> ClientCommand.PlayerInfo.serializer()
      else -> return ClientCommand.Unknown(clientId, type, json)
    }
    val command =  Json.parse(serializer, json)
    command.clientId = clientId
    return command
  } catch (e: Exception) {
    return ClientCommand.Malformed(clientId, type, json)
  }
}

sealed class ClientCommand {
  open var clientId: String = ""

  @Serializable data class Unknown(override var clientId: String, val type: String, val json: String) : ClientCommand()
  @Serializable data class Malformed(override var clientId: String, val type: String, val json: String) : ClientCommand()
  @Serializable data class PlayerInfo(val nationName: String) : ClientCommand()
}