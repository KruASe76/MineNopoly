package me.kruase.minenopoly

import me.kruase.minenopoly.util.isInGame
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Scoreboard
import net.md_5.bungee.api.ChatColor as CC


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
                    "${CC.GOLD}[${CC.GREEN}${instance.name}${CC.GOLD}]${CC.RESET} $message"
                )
            }
        }

        fun sendGameMessage(message: String?) {
            if (message == null) return
            instance.server.onlinePlayers.filter { it.isInGame() }.forEach {
                it.sendMessage(
                    "${CC.GOLD}[${CC.GREEN}${instance.name}${CC.GOLD}]${CC.RESET} $message"
                )
            }
        }
    }

    override fun onEnable() {
        instance = this
        userConfig = getUserConfig()
        scoreboard = MinenopolyScoreboard.new(server)

        getCommand("minenopoly")!!.setExecutor(MinenopolyCommands())

        server.pluginManager.registerEvents(MinenopolyEvents(), instance)
    }
}
