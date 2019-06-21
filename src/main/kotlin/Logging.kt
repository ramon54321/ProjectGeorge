import com.importre.crayon.*

fun logDebug(message: String) {
  println("[GAME] ${message}".blue())
}

fun logInfo(message: String) {
  println("[GAME] ${message}".white())
}

fun logWarning(message: String) {
  println("[GAME] ${message}".yellow())
}

fun logError(message: String) {
  println("[GAME] ${message}".red())
}