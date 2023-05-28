package me.kruase.minenopoly.events

import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.sendGameMessage
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.util.*
import org.bukkit.event.player.AsyncPlayerChatEvent
import net.md_5.bungee.api.ChatColor as CC


private const val diceSymbols = "①②③④⑤⑥"


fun chat(event: AsyncPlayerChatEvent) {
    val gameData = gameData ?: return
    if (!event.player.isInGame()) return
    if (!event.player.hasPluginPermission("get")) return

    event.isCancelled = true

    when {
        event.message.matches(Regex("""\+\d+""")) -> {
            val money: Int = try {
                event.message.drop(1).toInt()
            } catch (e: NumberFormatException) {
                throw IllegalStateException(
                    userConfig.messages.error["too-much-money"] ?: "Error: too-much-money"
                )
            }

            if (money == 0) return

            gameData.bank.inventory.money += money
            gameData.moneyInGame += money

            sendGameMessage(
                userConfig.messages.info["add-money"]
                    ?.replace("{player}", coloredName(event.player.playerListName))
                    ?.replace("{n}", asCurrency(money))
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
                    ?.replace("{player}", coloredName(event.player.playerListName))
                    ?.replace("{n}", asCurrency(money))
            )
        }
        event.message == "--" -> {
            gameData.bank.inventory.run {
                if (money != 0) {
                    gameData.moneyInGame -= money
                    sendGameMessage(
                        userConfig.messages.info["remove-money"]
                            ?.replace("{player}", coloredName(event.player.playerListName))
                            ?.replace("{n}", asCurrency(money))
                    )
                    money = 0
                }
                contents.filter {
                    it?.itemMeta?.persistentDataContainer?.hasAnyMark() == true
                }.forEach {
                    it.itemMeta!!.persistentDataContainer.run {
                        when {
                            hasMark("chance") -> gameData.chancesInGame -= it.amount
                            hasMark("community_chest") -> gameData.communityChestsInGame -= it.amount
                            hasMark("house") -> gameData.housesInGame -= it.amount
                            hasMark("hotel") -> gameData.hotelsInGame -= it.amount
                            hasMark("property") -> gameData.propertiesInGame[getPropertyType()] = false
                            else -> throw IllegalStateException()  // should never occur
                        }
                    }
                    sendGameMessage(
                        userConfig.messages.info["remove-item"]
                            ?.replace("{player}", coloredName(event.player.playerListName))
                            ?.replace(
                                "{item}",
                                it.itemMeta!!.displayName + (if (it.amount > 1) "${CC.RESET} ×${it.amount}" else "")
                            )
                    )
                    remove(it)
                }
            }
        }
        event.message == "?" -> {
            sendGameMessage(
                "${CC.BLUE}${
                    userConfig.messages.info["dice"]
                        ?.replace("{a}", diceSymbols.random().toString())
                        ?.replace("{b}", diceSymbols.random().toString())  
                }${CC.RESET}"
            )
        }
        else -> event.isCancelled = false
    }
}
