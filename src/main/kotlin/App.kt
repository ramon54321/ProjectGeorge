import commands.*
import core.Jobs
import core.State
import core.Ticker
import kotlinx.serialization.UnstableDefault
import math.Vector2i
import network.Server
import systems.weather.JobWeather
import kotlin.system.measureNanoTime

const val PORT = 8999
const val TICK_RATE = 1
const val TICKER_DELAY = 100L
const val RANDOM_SEED = 67
const val WORLD_WIDTH = 5
const val WORLD_HEIGHT = 5

fun test() {
  val time = measureNanoTime {
    (1..10000).forEach {
      State.toJson()
    }
  }
  println("Time: ${time/1000000}")
}

@UnstableDefault
fun main(args: Array<String>) {

//  test()
  start()

}

@UnstableDefault
fun start() {
  logInfo("Init Start")

  logDebug("Starting State: ${State.toJson()}")

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
    Jobs.addJob(JobWeather())
  }

  Ticker.onTick.subscribe {
    logInfo("Jobs: ${Jobs.getJobCount()}")

    Jobs.executeJobs()

    if(it.count == 7L) {
      // Fake action causing hexes to be added to nation
      State.getNationByName("Valkland")?.addHex(Vector2i(1,1))
      State.getNationByName("Valkland")?.addHex(Vector2i(1,0))
    }

    if(it.count == 10L) {
      logDebug(State.toJson())
    }

    broadcastServerCommand(ServerCommand.FullState(State.toJson()))
  }

  Ticker.onEnd.subscribe {
    logInfo("core.Ticker End")
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
