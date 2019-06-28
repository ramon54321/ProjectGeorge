import com.importre.crayon.*

fun buildMessage(message: String): String {
  return "[GAME] [${Thread.currentThread().name}] ${message}"
}

fun logDebug(message: String) {
  println(buildMessage(message).blue())
}

fun logInfo(message: String) {
  println(buildMessage(message).white())
}

fun logWarning(message: String) {
  println(buildMessage(message).yellow())
}

fun logError(message: String) {
  println(buildMessage(message).red())
}