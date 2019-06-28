package math

const val CIRCLE_RADIANS: Double = Math.PI * 2
const val HALFCIRCLE_RADIANS: Double = Math.PI
const val QUARTERCIRCLE_RADIANS: Double = Math.PI / 2
const val HEADING_E_RADIANS: Double  = (CIRCLE_RADIANS / 6) * 0
const val HEADING_NE_RADIANS: Double  = (CIRCLE_RADIANS / 6) * 1
const val HEADING_NW_RADIANS: Double  = (CIRCLE_RADIANS / 6) * 2
const val HEADING_W_RADIANS: Double  = (CIRCLE_RADIANS / 6) * 3
const val HEADING_SW_RADIANS: Double  = (CIRCLE_RADIANS / 6) * 4
const val HEADING_SE_RADIANS: Double  = (CIRCLE_RADIANS / 6) * 5

fun clamp01(x: Int): Int {
  return clamp01(x.toDouble()).toInt()
}

fun clamp01(x: Float): Float {
  return clamp01(x.toDouble()).toFloat()
}

fun clamp01(x: Double): Double {
  return Math.max(Math.min(1.toDouble(), x), 0.toDouble())
}

fun minAngle(radiansA: Float, radiansB: Float): Float {
  return minAngle(radiansA.toDouble(), radiansB.toDouble()).toFloat()
}

fun minAngle(radiansA: Double, radiansB: Double): Double {
  val radiansAMod = radiansA % CIRCLE_RADIANS
  val radiansBMod = radiansB % CIRCLE_RADIANS
  val absoluteDifference = Math.abs(radiansAMod - radiansBMod)
  return Math.min(CIRCLE_RADIANS - absoluteDifference, absoluteDifference)
}

fun lerp(a: Double, b: Double, t: Double): Double {
  return ((1.0 - t) * a) + (t * b)
}

fun lerp(a: Float, b: Float, t: Float): Float {
  return ((1.0F - t) * a) + (t * b)
}

fun normalized(min: Float, max: Float, floats: FloatArray): FloatArray {
  if (floats.isEmpty()) {
    return floatArrayOf()
  }
  val mi = floats.min()!!
  val ma = floats.max()!!

  val a = 1 / (ma - mi)
  return floats.map { (((it - mi) * a) * max) + min }.toFloatArray()
}