package core

import Utils
import WORLD_HEIGHT
import WORLD_WIDTH
import kotlinx.serialization.UnstableDefault
import math.Vector2i
import systems.weather.Weather
import java.util.*

class World {
  val hexByPosition: SortedMap<Vector2i, Hex> = sortedMapOf()
  val hexPositionsByNationName: MutableMap<String, MutableSet<Vector2i>> = mutableMapOf()

  init {
    for (y in 0..WORLD_HEIGHT) {
      for (x in 0..WORLD_WIDTH) {
        hexByPosition[Vector2i(x, y)] =
          Hex(Vector2i(x, y), Utils.random.nextInt(0, 10), Weather(Utils.random.nextFloat() * 30, Utils.random.nextFloat()))
      }
    }

    Nation.onAddHexToNation.subscribe {
      if (hexPositionsByNationName[it.nation.name] == null) {
        hexPositionsByNationName[it.nation.name] = mutableSetOf()
      }
      hexPositionsByNationName[it.nation.name]!!.add(it.hex.position)
    }

    Nation.onRemoveHexFromNation.subscribe {
      if (hexPositionsByNationName[it.nation.name] == null) {
        return@subscribe
      }
      hexPositionsByNationName[it.nation.name]!!.remove(it.hex.position)
    }
  }
}