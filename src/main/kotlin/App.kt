import commands.*
import kotlinx.serialization.UnstableDefault
import network.Server

const val PORT = 8999
const val TICK_RATE = 1

@UnstableDefault
fun main(args: Array<String>) {

  logInfo("Init Start")

  Server.onClientConnected.subscribe {

  }

  Server.onClientDisconnected.subscribe {

  }

  Server.onClientMessage.subscribe {
    val clientCommand = parseClientCommand(it.client.uuid, it.message)
    Commands.onCommand.onNext(clientCommand)
  }

  Ticker.onStart.subscribe {
    logInfo("Ticker Start")
  }

  Ticker.onTick.subscribe {

  }

  Ticker.onEnd.subscribe {
    logInfo("Ticker End")
  }

  Commands.onCommand.ofType(ClientCommand.PlayerInfo::class.java).subscribe {
    logInfo("Received Player Info Command for ${it.clientId}")
    val nation = State.nations[it.nation]

    if (nation == null) {
      dispatchServerCommand(it.clientId, ServerCommand.InvalidPlayerInfo("Invalid Nation"))
      return@subscribe
    }
  }

  Commands.onCommand.ofType(ClientCommand.Unknown::class.java).subscribe {
    logWarning("Received Unknown Command of type ${it.type}")
  }

  Commands.onCommand.ofType(ClientCommand.Malformed::class.java).subscribe {
    logWarning("Received Malformed Command of type ${it.type} with JSON of ${it.json}")
  }

  logInfo("Init End")
}
