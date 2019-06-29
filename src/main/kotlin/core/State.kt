package core

import Jsonable
import WORLD_HEIGHT
import WORLD_WIDTH
import math.Vector2i
import toJsonPropertiesMapObjectObject
import toJsonPropertiesMapStringListObject

object State : Jsonable {
  private val world = World()
  private val nations = mutableMapOf("Valkland" to Nation("Valkland"))

  fun getHexByPosition(position: Vector2i): Hex? {
    return world.hexByPosition[position]
  }

  fun getHexesByNation(nation: Nation): Set<Hex> {
    return State.getHexesByNation(nation.name)
  }

  fun getHexesByNation(nationName: String): Set<Hex> {
    val hexPositions = world.hexPositionsByNationName[nationName]
    val hexes = hexPositions?.map { world.hexByPosition[it] }?.filterNotNull()?.toSet()
    return hexes ?: setOf()
  }

  fun getNationByName(name: String): Nation? {
    return nations[name]
  }

  fun forEachHex(f: (hex: Hex) -> Unit) {
    for (y in 0 until WORLD_HEIGHT) {
      for (x in 0 until WORLD_WIDTH) {
        val hex = getHexByPosition(Vector2i(x, y))
        if (hex != null) f.invoke(hex)
      }
    }
  }

  fun forEachHexPosition(f: (position: Vector2i) -> Unit) {
    for (y in 0 until WORLD_HEIGHT) {
      for (x in 0 until WORLD_WIDTH) {
        f.invoke(Vector2i(x, y))
      }
    }
  }

  override fun toJson(): String {
    val hexByPosition = toJsonPropertiesMapObjectObject(world.hexByPosition.keys, world.hexByPosition.values, "hexByPosition")
    val hexPositionsByNationName = toJsonPropertiesMapStringListObject(world.hexPositionsByNationName.keys, world.hexPositionsByNationName.values, "hexPositionsByNationName")

    return """
      {
        "worldWidth":$WORLD_WIDTH,
        "worldHeight":$WORLD_HEIGHT,
        $hexByPosition,
        $hexPositionsByNationName
      }
      """.trim()
  }
}