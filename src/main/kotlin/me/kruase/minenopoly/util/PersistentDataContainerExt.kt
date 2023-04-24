package me.kruase.minenopoly.util

import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.Location
import me.kruase.minenopoly.Minenopoly.Companion.instance


private val stringType = PersistentDataType.STRING
private val byteType = PersistentDataType.BYTE
private const val value: Byte = 1
private const val propertyTypeKey = "property_type"


fun PersistentDataContainer.addMark(key: String) {
    set(NamespacedKey(instance, key), byteType, value)
}

fun PersistentDataContainer.hasMark(key: String): Boolean {
    return has(NamespacedKey(instance, key), byteType)
}

// Checks whether the PersistentDataContainer has any data with plugin's namespace
fun PersistentDataContainer.hasAnyMark(): Boolean {
    return "minenopoly" in keys.map { it.namespace }
}


fun PersistentDataContainer.addPropertyType(value: String) {
    set(NamespacedKey(instance, propertyTypeKey), stringType, value)
}

fun PersistentDataContainer.getPropertyType(): String {
    return get(NamespacedKey(instance, propertyTypeKey), stringType)!!
}


// itemNames are stored in the chunk's PersistentDataContainer
fun PersistentDataContainer.addItemName(item: ItemStack, location: Location) {
    set(NamespacedKey(
        instance,
        "${location.blockX}_${location.blockY}_${location.blockZ}"
    ), stringType, item.itemMeta!!.displayName)
}

fun PersistentDataContainer.hasItemName(location: Location): Boolean {
    return has(NamespacedKey(
        instance,
        "${location.blockX}_${location.blockY}_${location.blockZ}"
    ), stringType)
}

fun PersistentDataContainer.getItemName(location: Location): String? {
    return get(NamespacedKey(
        instance,
        "${location.blockX}_${location.blockY}_${location.blockZ}"
    ), stringType)
}

fun PersistentDataContainer.removeItemName(location: Location) {
    remove(NamespacedKey(instance, "${location.blockX}_${location.blockY}_${location.blockZ}"))
}
