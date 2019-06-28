package math

import kotlinx.serialization.Serializable
import Jsonable

@Serializable
data class Vector2i(val x: Int, val y: Int): Comparable<Vector2i>, Jsonable {

  override fun toJson(): String {
    return "{\"x\":\"$x\",\"y\":\"$y\"}"
  }

  override fun compareTo(other: Vector2i): Int {
    return when {
      y < other.y -> -1
      y > other.y -> 1
      else -> when {
        x < other.x -> -1
        x > other.x -> 1
        else -> 0
      }
    }
  }
}

@Serializable
data class MutableVector2f(var x: Float, var y: Float)