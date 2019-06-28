import kotlin.random.Random

interface Jsonable {
  fun toJson(): String
}

fun toJsonListListObject(items: Collection<Collection<Jsonable>>): String {
  return "[${items.map {
    "[${it.map { it.toJson() }.joinToString(",")}]"
  }.joinToString(",")}]"
}

fun toJsonListListString(items: Collection<Collection<String>>): String {
  return "[${items.map {
    "[${it.map { "$it" }.joinToString(",")}]"
  }.joinToString(",")}]"
}

fun toJsonListListFloat(items: Collection<Collection<Float>>): String {
  return "[${items.map {
    "[${it.joinToString(",")}]"
  }.joinToString(",")}]"
}

fun toJsonListListInt(items: Collection<Collection<Int>>): String {
  return "[${items.map {
    "[${it.joinToString(",")}]"
  }.joinToString(",")}]"
}

fun toJsonListObject(items: Collection<Jsonable>): String {
  return "[${items.map { it.toJson() }.joinToString(",")}]"
}

fun toJsonListJson(items: Collection<String>): String {
  return "[${items.map { "$it" }.joinToString(",")}]"
}

fun toJsonListString(items: Collection<String>): String {
  return "[${items.map { "\"$it\"" }.joinToString(",")}]"
}

fun toJsonListFloat(items: Collection<Float>): String {
  return "[${items.joinToString(",")}]"
}

fun toJsonListInt(items: Collection<Int>): String {
  return "[${items.joinToString(",")}]"
}

fun toJsonPropertiesMapObjectListObject(keys: Collection<Jsonable>, values: Collection<Collection<Jsonable>>, name: String): String {
  val keysJson = toJsonListObject(keys)
  val valuesJson = toJsonListListObject(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapStringListObject(keys: Collection<String>, values: Collection<Collection<Jsonable>>, name: String): String {
  val keysJson = toJsonListString(keys)
  val valuesJson = toJsonListListObject(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapIntListObject(keys: Collection<Int>, values: Collection<Collection<Jsonable>>, name: String): String {
  val keysJson = toJsonListInt(keys)
  val valuesJson = toJsonListListObject(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapObjectListString(keys: Collection<Jsonable>, values: Collection<Collection<String>>, name: String): String {
  val keysJson = toJsonListObject(keys)
  val valuesJson = toJsonListListString(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapStringListString(keys: Collection<String>, values: Collection<Collection<String>>, name: String): String {
  val keysJson = toJsonListString(keys)
  val valuesJson = toJsonListListString(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapIntListString(keys: Collection<Int>, values: Collection<Collection<String>>, name: String): String {
  val keysJson = toJsonListInt(keys)
  val valuesJson = toJsonListListString(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapObjectListFloat(keys: Collection<Jsonable>, values: Collection<Collection<Float>>, name: String): String {
  val keysJson = toJsonListObject(keys)
  val valuesJson = toJsonListListFloat(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapStringListFloat(keys: Collection<String>, values: Collection<Collection<Float>>, name: String): String {
  val keysJson = toJsonListString(keys)
  val valuesJson = toJsonListListFloat(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapIntListFloat(keys: Collection<Int>, values: Collection<Collection<Float>>, name: String): String {
  val keysJson = toJsonListInt(keys)
  val valuesJson = toJsonListListFloat(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapObjectListInt(keys: Collection<Jsonable>, values: Collection<Collection<Int>>, name: String): String {
  val keysJson = toJsonListObject(keys)
  val valuesJson = toJsonListListInt(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapStringListInt(keys: Collection<String>, values: Collection<Collection<Int>>, name: String): String {
  val keysJson = toJsonListString(keys)
  val valuesJson = toJsonListListInt(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapIntListInt(keys: Collection<Int>, values: Collection<Collection<Int>>, name: String): String {
  val keysJson = toJsonListInt(keys)
  val valuesJson = toJsonListListInt(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapObjectObject(keys: Collection<Jsonable>, values: Collection<Jsonable>, name: String): String {
  val keysJson = toJsonListObject(keys)
  val valuesJson = toJsonListObject(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapStringObject(keys: Collection<String>, values: Collection<Jsonable>, name: String): String {
  val keysJson = toJsonListString(keys)
  val valuesJson = toJsonListObject(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapIntObject(keys: Collection<Int>, values: Collection<Jsonable>, name: String): String {
  val keysJson = toJsonListInt(keys)
  val valuesJson = toJsonListObject(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapObjectString(keys: Collection<Jsonable>, values: Collection<String>, name: String): String {
  val keysJson = toJsonListObject(keys)
  val valuesJson = toJsonListString(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapStringString(keys: Collection<String>, values: Collection<String>, name: String): String {
  val keysJson = toJsonListString(keys)
  val valuesJson = toJsonListString(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapIntString(keys: Collection<Int>, values: Collection<String>, name: String): String {
  val keysJson = toJsonListInt(keys)
  val valuesJson = toJsonListString(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapObjectInt(keys: Collection<Jsonable>, values: Collection<Int>, name: String): String {
  val keysJson = toJsonListObject(keys)
  val valuesJson = toJsonListInt(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapStringInt(keys: Collection<String>, values: Collection<Int>, name: String): String {
  val keysJson = toJsonListString(keys)
  val valuesJson = toJsonListInt(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapIntInt(keys: Collection<Int>, values: Collection<Int>, name: String): String {
  val keysJson = toJsonListInt(keys)
  val valuesJson = toJsonListInt(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapObjectFloat(keys: Collection<Jsonable>, values: Collection<Float>, name: String): String {
  val keysJson = toJsonListObject(keys)
  val valuesJson = toJsonListFloat(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapStringFloat(keys: Collection<String>, values: Collection<Float>, name: String): String {
  val keysJson = toJsonListString(keys)
  val valuesJson = toJsonListFloat(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

fun toJsonPropertiesMapIntFloat(keys: Collection<Int>, values: Collection<Float>, name: String): String {
  val keysJson = toJsonListInt(keys)
  val valuesJson = toJsonListFloat(values)
  return "\"${name}Keys\":$keysJson,\"${name}Values\":$valuesJson"
}

object Utils {
  val random = Random(RANDOM_SEED)
}
