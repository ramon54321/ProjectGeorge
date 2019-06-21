import com.importre.crayon.red
import com.importre.crayon.white
import com.importre.crayon.yellow

fun logInfo(message: String) {
  println("[GAME] ${message}".white())
}

fun logWarning(message: String) {
  println("[GAME] ${message}".yellow())
}

fun logError(message: String) {
  println("[GAME] ${message}".red())
}