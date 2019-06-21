package world

import kotlinx.serialization.Serializable
import math.Vector2i

@Serializable
class Hex(val position: Vector2i, val height: Int) {

}