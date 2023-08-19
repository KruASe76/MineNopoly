package me.kruase.minenopoly.util

import me.kruase.minenopoly.Minenopoly.Companion.instance
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack


private const val key: String = "money"


var Inventory.money: Int
    get() = userConfig.materials.money.map { (cost, material) ->
        contents.filter { it?.type == material && it.itemMeta?.persistentDataContainer?.hasMark(key) == true }
            .sumOf { it.amount } * cost
    }.sum()
    set(value) {
        removeItem(
            *contents.filter {
                it?.itemMeta?.persistentDataContainer?.hasMark(key) == true
            }.toTypedArray()
        )

        var mutableValue = value
        userConfig.materials.money.forEach { (cost, material) ->
            val amount = mutableValue / cost
            addItem(
                ItemStack(material, amount).apply {
                    itemMeta = itemMeta!!.apply {
                        setDisplayName(asCurrency(cost))
                        persistentDataContainer.addMark(key)
                    }
                }
            ).values.forEach {
                instance.server.scheduler.callSyncMethod(instance) {
                    location?.world?.dropItemNaturally(location!!, it)
                        ?: throw IllegalStateException(
                            userConfig.messages.error["unable-to-add-item"] ?: "Error: unable-to-add-item"
                        )
                }
            }
            mutableValue %= cost
        }
    }
