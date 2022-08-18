package me.kruase.minenopoly.events

import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.ChatColor
import me.kruase.minenopoly.Minenopoly.Companion.mConfig
import me.kruase.minenopoly.Minenopoly.Companion.gameRunning
import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.sendGlobalMessage
import me.kruase.minenopoly.util.*


const val diceSymbols = "①②③④⑤⑥"

fun chat(event: AsyncPlayerChatEvent) {
    if (!gameRunning) return
    if (!event.player.isInGame()) return
    if (!event.player.hasPermission("minenopoly.get")) return

    event.isCancelled = true

    when {
        event.message.matches(Regex("""\+\d+""")) -> {
            val money: Int = event.message.drop(1).toInt()
            if (money == 0) return

            gameData!!.bank.inventory.money += money
            gameData!!.moneyInGame += money

            sendGlobalMessage(
                mConfig.messages.info["add-money"]
                    ?.replace("{player}", getColoredName(event.player.playerListName))
                    ?.replace("{n}", getMoneyItemName(money))
            )
        }
        event.message.matches(Regex("""-\d+""")) -> {
            val money: Int = event.message.drop(1).toInt()
            if (money == 0) return

            if (money > gameData!!.bank.inventory.money) throw IllegalStateException(
                mConfig.messages.error["not-enough-money"] ?: "Error: not-enough-money"
            )

            gameData!!.bank.inventory.money -= money
            gameData!!.moneyInGame -= money

            sendGlobalMessage(
                mConfig.messages.info["remove-money"]
                    ?.replace("{player}", getColoredName(event.player.playerListName))
                    ?.replace("{n}", getMoneyItemName(money))
            )
        }
        event.message == "--" -> {  // TODO: test this
            gameData!!.bank.inventory.run {
                if (money != 0) {
                    gameData!!.moneyInGame -= money
                    sendGlobalMessage(
                        mConfig.messages.info["remove-money"]
                            ?.replace("{player}", getColoredName(event.player.playerListName))
                            ?.replace("{n}", getMoneyItemName(money))
                    )
                    money = 0
                }
                contents.filter {
                    it?.itemMeta?.persistentDataContainer?.hasAnyMark() == true
                }.forEach {
                    it.itemMeta!!.persistentDataContainer.run {
                        when {
                            hasMark("chance") -> gameData!!.chancesInGame -= 1
                            hasMark("community_chest") -> gameData!!.communityChestsInGame -= 1
                            hasMark("house") -> gameData!!.housesInGame -= 1
                            hasMark("hotel") -> gameData!!.hotelsInGame -= 1
                            hasMark("property") -> gameData!!.propertiesInGame[getPropertyType()] = false
                        }
                    }
                    sendGlobalMessage(
                        mConfig.messages.info["remove-item"]
                            ?.replace("{player}", getColoredName(event.player.playerListName))
                            ?.replace("{item}", it.itemMeta!!.displayName)
                    )
                    remove(it)
                }
            }
        }
        event.message == "?" -> { sendGlobalMessage("${ChatColor.BLUE}Dice:${ChatColor.RESET} ${dice()} ${dice()}") }
        else -> event.isCancelled = false
    }
}

fun dice(): Char {
    return diceSymbols.random()
}
