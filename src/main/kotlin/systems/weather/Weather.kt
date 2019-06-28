package systems.weather

import Jsonable

interface IWeather {
  val weather: Weather
}

data class Weather(var temperature: Float, var humidity: Float) : Jsonable {
  override fun toJson(): String {
    return "{\"temperature\":$temperature,\"humidity\":$humidity}"
  }
}