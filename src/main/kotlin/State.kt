import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import math.Vector2i
import world.Hex
import world.World

@UnstableDefault
object State {
  private val world = World()
  private val nations = mutableMapOf("Valkland" to Nation("Valkland"))

  fun getHexByPosition(position: Vector2i): Hex? {
    return world.hexByPosition[position]
  }

  fun getHexesByNationName(nationName: String): Set<Hex> {
    val hexPositions = world.hexPositionsByNationName[nationName]
    val hexes = hexPositions?.map { world.hexByPosition[it] }?.filter { it != null }?.toSet()
    return hexes as Set<Hex>? ?: setOf()
  }

  fun getNationByName(name: String): Nation? {
    return nations[name]
  }

  fun getJson(): String {
    return Json.stringify(World.serializer(), world)
  }
}