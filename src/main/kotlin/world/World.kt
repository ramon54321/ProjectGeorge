package world

import WORLD_HEIGHT
import WORLD_WIDTH
import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import math.Vector2i

@UnstableDefault
@Serializable
class World {
  val hexByPosition: MutableMap<Vector2i, Hex> = mutableMapOf()
  val hexPositionsByNationName: MutableMap<String, MutableSet<Vector2i>> = mutableMapOf()

  init {
    for (y in 0..WORLD_HEIGHT) {
      for (x in 0..WORLD_WIDTH) {
        hexByPosition[Vector2i(x, y)] = Hex(Vector2i(x, y), Utils.random.nextInt(0, 10))
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