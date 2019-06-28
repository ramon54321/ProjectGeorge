package systems.weather

import WORLD_HEIGHT
import WORLD_WIDTH
import core.Job
import core.State
import math.MutableVector2f
import math.Noise

class JobWeather : Job {
  private val windVector = MutableVector2f(0.002F, 0.002F)
  private val offset = MutableVector2f(0F, 0F)
  private val frequency = 9F

  override fun execute() {
    State.forEachHex {
      val x = ((it.position.x / WORLD_WIDTH) * frequency) + offset.x
      val y = ((it.position.y / WORLD_HEIGHT) * frequency) + offset.y

      val noise = Noise.perlin(x, y)

      val temperature = ((noise + 1) / 2) * 50
      it.weather.temperature = temperature

      offset.x = offset.x + windVector.x
      offset.y = offset.y + windVector.y
    }
  }
}