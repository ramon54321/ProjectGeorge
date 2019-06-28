package math

//fun dotGridGradient(ix: Int, iy: Int, x: Float, y: Float): Float {
//  // Compute the distance vector
//  val dx = x - ix.toFloat()
//  val dy = y - iy.toFloat()
//
////  println("dx: $dx")
////  println("dy: $dy")
//
//  val index = Math.abs((iy + ix) % 256)
//
////  println("Index: $index")
//
//  // Compute the dot-product
//  return dx * permutation[index] + dy * permutation[index]
//}
//
//fun perlin(x: Float, y: Float): Float {
//
//  // Determine grid cell coordinates
//  val x0 = x.toInt()
//  val x1 = x0 + 1
//  val y0 = y.toInt()
//  val y1 = y0 + 1
//
//  // Determine interpolation weights
//  // Could also use higher order polynomial/s-curve here
//  val sx = x - x0.toFloat()
//  val sy = y - y0.toFloat()
//
////  println("x: $x")
////  println("x0: $x0")
//
////  println("sx: $sx")
////  println("sy: $sy")
//
//  // Interpolate between grid point gradients
//  val n0 = dotGridGradient(x0, y0, x, y)
//  val n1 = dotGridGradient(x1, y0, x, y)
//  val ix0 = lerp(n0, n1, sx)
//
////  println("n0: $n0")
////  println("n1: $n1")
////  println("ix0: $ix0")
//
//  val n2 = dotGridGradient(x0, y1, x, y)
//  val n3 = dotGridGradient(x1, y1, x, y)
//  val ix1 = lerp(n2, n3, sx)
//
////  println("n2: $n2")
////  println("n3: $n3")
////  println("ix1: $ix1")
//
//  return (lerp(ix0, ix1, sy) + 64F) / 128F
//}

data class Gradient(val x: Int, val y: Int) {
  fun dot(x: Float, y: Float): Float {
    return this.x * x + this.y * y
  }
}

object Noise {
  private val permutationTable = arrayOf(
    151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225,
    140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148,
    247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32,
    57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175,
    74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122,
    60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54,
    65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169,
    200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64,
    52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212,
    207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213,
    119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9,
    129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104,
    218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241,
    81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157,
    184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93,
    222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180
  )

  private val gradientTable = arrayOf(
    Gradient(1, 1),
    Gradient(-1, 1),
    Gradient(1, -1),
    Gradient(-1, -1),
    Gradient(1, 0),
    Gradient(-1, 0),
    Gradient(0, 1),
    Gradient(0, -1)
  )

  private val permutations = mutableListOf<Int>()
  private val gradients = mutableListOf<Gradient>()

  init {
    val seed = 45678

    for (i in 0..511) {
      val v = permutationTable[i % 255] xor (seed % 255)
      permutations.add(v)
      gradients.add(gradientTable[v % 7])
    }
  }

  fun floor(x: Float): Int {
    val ix = x.toInt()
    return if (x < ix) ix - 1 else ix
  }

  fun perlin(x: Float, y: Float): Float {
    val ix = floor(x)
    val iy = floor(y)
    val ox = x - ix
    val oy = y - iy
    val ixw = ix and 255
    val iyw = iy and 255

    val n00 = gradients[ixw + permutations[iyw]].dot(ox, oy)
    val n01 = gradients[ixw + permutations[iyw + 1]].dot(ox, oy - 1)
    val n10 = gradients[ixw + 1 + permutations[iyw]].dot(ox - 1, oy)
    val n11 = gradients[ixw + 1 + permutations[iyw + 1]].dot(ox - 1, oy - 1)

    val u = fade(ox)
    val v = fade(oy)

    return lerp(
      lerp(n00, n10, u),
      lerp(n01, n11, u),
      v
    )
  }

  private fun fade(t: Float): Float {
    return t * t * t * (t * (t * 6 - 15) + 10)
  }
}





