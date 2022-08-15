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
                    ?.replace("{player}",
                        event.player.playerListName.run {
                            if (startsWith(ChatColor.COLOR_CHAR)) "$this${ChatColor.RESET}"
                            else "${ChatColor.YELLOW}$this${ChatColor.RESET}"
                        }
                    )
                    ?.replace("{n}", "${ChatColor.GREEN}$money ${ChatColor.STRIKETHROUGH}M${ChatColor.RESET}")
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
                    ?.replace("{player}",
                        event.player.playerListName.run {
                            if (startsWith(ChatColor.COLOR_CHAR)) "$this${ChatColor.RESET}"
                            else "${ChatColor.YELLOW}$this${ChatColor.RESET}"
                        }
                    )
                    ?.replace("{n}", "${ChatColor.GREEN}$money ${ChatColor.STRIKETHROUGH}M${ChatColor.RESET}")
            )
        }
        event.message == "--" -> {  // TODO: test this
            gameData!!.bank.inventory.run {
                if (money != 0) {
                    gameData!!.moneyInGame -= money
                    sendGlobalMessage(
                        mConfig.messages.info["remove-money"]
                            ?.replace("{player}",
                                event.player.playerListName.run {
                                    if (startsWith(ChatColor.COLOR_CHAR)) "$this${ChatColor.RESET}"
                                    else "${ChatColor.YELLOW}$this${ChatColor.RESET}"
                                }
                            )
                            ?.replace("{n}", "${ChatColor.GREEN}$money ${ChatColor.STRIKETHROUGH}M${ChatColor.RESET}")
                    )
                }
                contents.filter {
                    it?.itemMeta?.persistentDataContainer?.hasAnyMark() == true
                }.forEach {
                    it.itemMeta!!.persistentDataContainer.run {
                        when {
                            hasMark("chance") -> {
                                gameData!!.chancesInGame--
                                sendGlobalMessage(
                                    mConfig.messages.info["remove-action-card"]
                                        ?.replace("{player}",
                                            event.player.playerListName.run {
                                                if (startsWith(ChatColor.COLOR_CHAR)) "$this${ChatColor.RESET}"
                                                else "${ChatColor.YELLOW}$this${ChatColor.RESET}"
                                            }
                                        )
                                        ?.replace("{action_card}", "${ChatColor.GOLD}Chance${ChatColor.RESET}")
                                )
                            }
                            hasMark("community_chest") -> {
                                gameData!!.communityChestsInGame--
                                sendGlobalMessage(
                                    mConfig.messages.info["remove-action-card"]
                                        ?.replace("{player}",
                                            event.player.playerListName.run {
                                                if (startsWith(ChatColor.COLOR_CHAR)) "$this${ChatColor.RESET}"
                                                else "${ChatColor.YELLOW}$this${ChatColor.RESET}"
                                            }
                                        )
                                        ?.replace("{action_card}", "${ChatColor.AQUA}Community Chest${ChatColor.RESET}")
                                )
                            }
                            hasMark("house") -> {
                                gameData!!.housesInGame--
                                sendGlobalMessage(
                                    mConfig.messages.info["remove-building"]
                                        ?.replace("{player}",
                                            event.player.playerListName.run {
                                                if (startsWith(ChatColor.COLOR_CHAR)) "$this${ChatColor.RESET}"
                                                else "${ChatColor.YELLOW}$this${ChatColor.RESET}"
                                            }
                                        )
                                        ?.replace("{building}", "${ChatColor.DARK_GREEN}House${ChatColor.RESET}")
                                )
                            }
                            hasMark("hotel") -> {
                                gameData!!.hotelsInGame--
                                sendGlobalMessage(
                                    mConfig.messages.info["remove-building"]
                                        ?.replace("{player}",
                                            event.player.playerListName.run {
                                                if (startsWith(ChatColor.COLOR_CHAR)) "$this${ChatColor.RESET}"
                                                else "${ChatColor.YELLOW}$this${ChatColor.RESET}"
                                            }
                                        )
                                        ?.replace("{building}", "${ChatColor.DARK_RED}Hotel${ChatColor.RESET}")
                                )
                            }
                            hasMark("property") -> {
                                gameData!!.propertiesInGame[getPropertyType()] = false
                                sendGlobalMessage(
                                    mConfig.messages.info["remove-property"]
                                        ?.replace("{player}",
                                            event.player.playerListName.run {
                                                if (startsWith(ChatColor.COLOR_CHAR)) "$this${ChatColor.RESET}"
                                                else "${ChatColor.YELLOW}$this${ChatColor.RESET}"
                                            }
                                        )
                                        ?.replace("{property}", "${it.itemMeta!!.displayName}${ChatColor.RESET}")
                                )
                            }
                        }
                    }
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
