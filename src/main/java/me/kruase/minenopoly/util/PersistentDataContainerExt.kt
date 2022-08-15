package me.kruase.minenopoly.util

import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import org.bukkit.NamespacedKey
import me.kruase.minenopoly.Minenopoly.Companion.instance


val stringType: PersistentDataType<String, String> = PersistentDataType.STRING
val byteType: PersistentDataType<Byte, Byte> = PersistentDataType.BYTE
const val value: Byte = 1
const val propertyTypeKey: String = "property_type"


fun PersistentDataContainer.addMark(key: String) {
    return set(NamespacedKey(instance, key), byteType, value)
}

fun PersistentDataContainer.hasMark(key: String): Boolean {
    return has(NamespacedKey(instance, key), byteType)
}

// Checks whether the PersistentDataContainer has any data with plugin's namespace
fun PersistentDataContainer.hasAnyMark(): Boolean {
    return keys.map { it.namespace }.contains("minenopoly")
}

fun PersistentDataContainer.getPropertyType(): String {
    return get(NamespacedKey(instance, propertyTypeKey), stringType)!!
}
