package core

import math.Vector2i
import systems.weather.IWeather
import systems.weather.Weather
import Jsonable

class Hex(val position: Vector2i, val height: Int, override val weather: Weather) :
  IWeather, Comparable<Hex>, Jsonable {

  val nationNames: MutableSet<String> = mutableSetOf()

  override fun toJson(): String {
    return "{\"position\":${position.toJson()},\"weather\":${weather.toJson()}}"
  }

  override fun compareTo(other: Hex): Int {
    return when {
      position.y < other.position.y -> -1
      position.y > other.position.y -> 1
      else -> when {
        position.x < other.position.x -> -1
        position.x > other.position.x -> 1
        else -> 0
      }
    }
  }

  val isEven: Boolean
    get() = position.y % 2 == 0

  val W
    get() = { State.getHexByPosition(Vector2i(position.x - 1, position.y)) }
  val E
    get() = { State.getHexByPosition(Vector2i(position.x + 1, position.y)) }
  val NW
    get() = {
      if (isEven) State.getHexByPosition(
        Vector2i(
          position.x - 1,
          position.y + 1
        )
      ) else State.getHexByPosition(Vector2i(position.x, position.y + 1))
    }
  val NE
    get() = {
      if (isEven) State.getHexByPosition(
        Vector2i(
          position.x,
          position.y + 1
        )
      ) else State.getHexByPosition(Vector2i(position.x + 1, position.y + 1))
    }
  val SW
    get() = {
      if (isEven) State.getHexByPosition(
        Vector2i(
          position.x - 1,
          position.y - 1
        )
      ) else State.getHexByPosition(Vector2i(position.x, position.y - 1))
    }
  val SE
    get() = {
      if (isEven) State.getHexByPosition(
        Vector2i(
          position.x,
          position.y - 1
        )
      ) else State.getHexByPosition(Vector2i(position.x + 1, position.y - 1))
    }
}
