package me.kruase.minenopoly.util

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.PlayerInventory
import org.bukkit.inventory.ItemStack
import me.kruase.minenopoly.MinenopolyConfig.Materials


var Inventory.money: Int
    get() {
        val allItems = when (this) {
            is PlayerInventory -> contents + holder!!.itemOnCursor
            else -> contents
        }

        return Materials.money.map { (cost, material) ->
            allItems.filter { it?.type == material && it.itemMeta?.displayName == getMoneyItemName(cost.toString()) }
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
        Materials.money.forEach { (cost, material) ->
            val amount = value / cost
            val overflow = addItem(
                ItemStack(material, amount).apply { itemMeta = itemMeta
                    ?.apply { setDisplayName(getMoneyItemName(cost.toString())) }}
            )
            for (itemStack in overflow.values) {
                location?.world?.dropItem(location!!, itemStack)
                    ?: throw IllegalArgumentException("Unable to fill the inventory")
            }
            value %= cost
        }
    }

