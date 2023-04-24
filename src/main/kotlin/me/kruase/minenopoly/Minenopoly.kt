package me.kruase.minenopoly

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.ChatColor
import me.kruase.minenopoly.util.isInGame


class Minenopoly : JavaPlugin() {
    companion object {
        lateinit var instance: Minenopoly
        lateinit var userConfig: MinenopolyConfig
        lateinit var scoreboard: Scoreboard

        var gameData: MinenopolyGameData? = null

        fun sendGlobalMessage(message: String?) {
            if (message == null) return
            instance.server.onlinePlayers.forEach {
                it.sendMessage(
                    "${ChatColor.GOLD}[${ChatColor.GREEN}MineNopoly${ChatColor.GOLD}]${ChatColor.RESET} $message"
                )
            }
        }

        fun sendGameMessage(message: String?) {
            if (message == null) return
            instance.server.onlinePlayers.filter { it.isInGame() }.forEach {
                it.sendMessage(
                    "${ChatColor.GOLD}[${ChatColor.GREEN}MineNopoly${ChatColor.GOLD}]${ChatColor.RESET} $message"
                )
            }
        }
    }

    override fun onEnable() {
        instance = this
        userConfig = getUserConfig()
        scoreboard = MinenopolyScoreboard.new(server)

        getCommand("monopoly")!!.setExecutor(MinenopolyCommands())

        server.pluginManager.registerEvents(MinenopolyEvents(), instance)
    }
}
