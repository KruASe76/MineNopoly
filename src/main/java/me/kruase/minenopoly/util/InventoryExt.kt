package me.kruase.minenopoly.util

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.PlayerInventory
import org.bukkit.inventory.ItemStack
import me.kruase.minenopoly.Minenopoly.Companion.mConfig


var Inventory.money: Int
    get() {
        val allItems = when (this) {
            is PlayerInventory -> contents + holder!!.itemOnCursor
            else -> contents
        }

        return mConfig.materials.money.map { (cost, material) ->
            allItems.filter { it?.type == material && it.itemMeta?.persistentDataContainer?.hasMark() == true }
                .sumOf { it.amount } * cost
        }.sum()
    }
    set(value) {
        removeItem(
            *contents.filter {
                Regex(getMoneyItemName("\\d+"))
                    .matches(it?.itemMeta?.displayName ?: "")
            }.toTypedArray()
        )

        @Suppress("NAME_SHADOWING")
        var value = value
        mConfig.materials.money.forEach { (cost, material) ->
            val amount = value / cost
            val overflow = addItem(
                ItemStack(material, amount).apply { itemMeta = itemMeta
                    ?.apply {
                        setDisplayName(getMoneyItemName(cost.toString()))
                        persistentDataContainer.addMark()
                    }}
            )
            for (itemStack in overflow.values) {
                location?.world?.dropItem(location!!, itemStack)
                    ?: throw IllegalArgumentException("Unable to fill the inventory")
            }
            value %= cost
        }
    }
