package me.kruase.minenopoly.events

import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.ChatColor
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.sendGameMessage
import me.kruase.minenopoly.util.*


private const val diceSymbols = "①②③④⑤⑥"


fun chat(event: AsyncPlayerChatEvent) {
    val gameData = gameData ?: return
    if (!event.player.isInGame()) return
    if (!event.player.hasPluginPermission("get")) return

    event.isCancelled = true

    when {
        event.message.matches(Regex("""\+\d+""")) -> {
            val money: Int = event.message.drop(1).toInt()
            if (money == 0) return

            gameData.bank.inventory.money += money
            gameData.moneyInGame += money

            sendGameMessage(
                userConfig.messages.info["add-money"]
                    ?.replace("{player}", getColoredName(event.player.playerListName))
                    ?.replace("{n}", getMoneyItemName(money))
            )
        }
        event.message.matches(Regex("""-\d+""")) -> {
            val money: Int = event.message.drop(1).toInt()
            if (money == 0) return

            if (money > gameData.bank.inventory.money) throw IllegalStateException(
                userConfig.messages.error["not-enough-money"] ?: "Error: not-enough-money"
            )

            gameData.bank.inventory.money -= money
            gameData.moneyInGame -= money

            sendGameMessage(
                userConfig.messages.info["remove-money"]
                    ?.replace("{player}", getColoredName(event.player.playerListName))
                    ?.replace("{n}", getMoneyItemName(money))
            )
        }
        event.message == "--" -> {  // TODO: test this
            gameData.bank.inventory.run {
                if (money != 0) {
                    gameData.moneyInGame -= money
                    sendGameMessage(
                        userConfig.messages.info["remove-money"]
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
                            hasMark("chance") -> gameData.chancesInGame--
                            hasMark("community_chest") -> gameData.communityChestsInGame--
                            hasMark("house") -> gameData.housesInGame--
                            hasMark("hotel") -> gameData.hotelsInGame--
                            hasMark("property") -> gameData.propertiesInGame[getPropertyType()] = false
                            else -> {}
                        }
                    }
                    sendGameMessage(
                        userConfig.messages.info["remove-item"]
                            ?.replace("{player}", getColoredName(event.player.playerListName))
                            ?.replace("{item}", it.itemMeta!!.displayName)
                    )
                    remove(it)
                }
            }
        }
        event.message == "?" -> {
            sendGameMessage(
                "${ChatColor.BLUE}${
                    userConfig.messages.info["dice"]
                        ?.replace("{a}", dice())
                        ?.replace("{b}", dice())  
                }${ChatColor.RESET}"
            )
        }
        else -> event.isCancelled = false
    }
}


fun dice(): String {
    return diceSymbols.random().toString()
}
