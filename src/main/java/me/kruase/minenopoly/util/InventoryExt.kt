package me.kruase.minenopoly.util

import me.kruase.minenopoly.Minenopoly
import org.bukkit.inventory.Inventory
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import me.kruase.minenopoly.Minenopoly.Companion.mConfig


const val key: String = "money"

var Inventory.money: Int
    get() = mConfig.materials.money.map { (cost, material) ->
        contents.filter { it?.type == material && it.itemMeta?.persistentDataContainer?.hasMark(key) == true }
            .sumOf { it.amount } * cost
    }.sum()
    set(value) {
        removeItem(
            *contents.filter {
                it?.itemMeta?.persistentDataContainer?.hasMark(key) == true
            }.toTypedArray()
        )

        @Suppress("NAME_SHADOWING")
        var value = value
        mConfig.materials.money.forEach { (cost, material) ->
            val amount = value / cost
            val overflow = addItem(
                ItemStack(material, amount).apply { itemMeta = itemMeta!!
                    .apply {
                        setDisplayName(getMoneyItemName(cost))
                        persistentDataContainer.addMark(key)
                    }
                }
            )
            for (itemStack in overflow.values) {
                location?.world?.dropItem(location!!, itemStack)
                    ?: throw IllegalStateException(
                        mConfig.messages.error["unable-to-add-item"] ?: "Error: unable-to-add-item"
                    )
            }
            value %= cost
        }
    }

fun Player.isInGame(): Boolean {
    return inventory.contents.any { it?.itemMeta?.persistentDataContainer?.hasAnyMark() == true } ||
            (Minenopoly.gameData?.bank?.location?.distance(location) ?: 32.toDouble()) < 32
}
