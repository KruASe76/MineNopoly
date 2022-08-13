package me.kruase.minenopoly.util

import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import org.bukkit.NamespacedKey
import me.kruase.minenopoly.Minenopoly.Companion.instance


val key: NamespacedKey = NamespacedKey(instance, "minenopoly")
val type: PersistentDataType<Byte, Byte> = PersistentDataType.BYTE
const val value: Byte = 1

fun PersistentDataContainer.addMark() {
    return set(key, type, value)
}

fun PersistentDataContainer.hasMark(): Boolean {
    return has(key, type)
}
