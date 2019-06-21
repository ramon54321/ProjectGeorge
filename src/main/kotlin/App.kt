import commands.*
import kotlinx.serialization.UnstableDefault
import math.Vector2i
import network.Server

const val PORT = 8999
const val TICK_RATE = 1
const val RANDOM_SEED = 67
const val WORLD_WIDTH = 3
const val WORLD_HEIGHT = 3

@UnstableDefault
fun main(args: Array<String>) {

  logInfo("Init Start")

  logDebug("Starting State: ${State.getJson()}")

  Server.onClientConnected.subscribe {

  }

  Server.onClientDisconnected.subscribe {

  }

  Server.onClientMessage.subscribe {
    val clientCommand = parseClientCommand(it.client.uuid, it.message)
    Commands.onCommand.onNext(clientCommand)
  }

  Ticker.onEarlyStart.subscribe {
    logInfo("Ticker Early Start")
  }

  Ticker.onStart.subscribe {
    logInfo("Ticker Start")
  }

  Ticker.onTick.subscribe {
    if(it.count < 15) {
      logDebug("Debug Tick: ${it.count}")
    }

    if(it.count == 7L) {
      State.getNationByName("Valkland")?.addHex(Vector2i(1,2))
      State.getNationByName("Valkland")?.addHex(Vector2i(2,2))
    }

    if(it.count == 10L) {
      logDebug(State.getJson())
      logDebug(State.getHexesByNationName("Valkland").toString())
    }
  }

  Ticker.onEnd.subscribe {
    logInfo("Ticker End")
  }

  Commands.onCommand.ofType(ClientCommand.PlayerInfo::class.java).subscribe {
    logInfo("Received Player Info Command for ${it.clientId}")

    val nation = State.getNationByName(it.nationName)
    if (nation == null) {
      dispatchServerCommand(it.clientId, ServerCommand.InvalidPlayerInfo("Invalid Nation Name"))
      return@subscribe
    }

    val client = Server.getClientById(it.clientId)
    if (client == null) {
      dispatchServerCommand(it.clientId, ServerCommand.InvalidPlayerInfo("Invalid Client ID"))
      logError("Invalid Client ID in Player Info")
      return@subscribe
    }

    client.nation = nation
    nation.clients.add(client)
  }

  Commands.onCommand.ofType(ClientCommand.Unknown::class.java).subscribe {
    logWarning("Received Unknown Command of type ${it.type}")
  }

  Commands.onCommand.ofType(ClientCommand.Malformed::class.java).subscribe {
    logWarning("Received Malformed Command of type ${it.type} with JSON of ${it.json}")
  }

  logInfo("Init End")
}
