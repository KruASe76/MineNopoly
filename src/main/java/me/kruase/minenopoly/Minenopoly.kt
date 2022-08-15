package me.kruase.minenopoly

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.ChatColor


class Minenopoly : JavaPlugin() {
    companion object {
        lateinit var instance: Minenopoly
        lateinit var scoreboard: Scoreboard
        lateinit var mConfig: MinenopolyConfig

        var gameRunning: Boolean = false
        var gameData: MinenopolyGameData? = null

        fun sendGlobalMessage(message: String?) {
            if (message == null) return
            instance.server.onlinePlayers.forEach {
                it.sendMessage(
                    "${ChatColor.GOLD}[${ChatColor.GREEN}MineNopoly${ChatColor.GOLD}]${ChatColor.RESET} ${message}"
                )
            }
        }
    }

    override fun onEnable() {
        instance = this
        scoreboard = MinenopolyScoreboard.new(server)
        mConfig = getMinenopolyConfig(instance)

        getCommand("monopoly")!!.setExecutor(MinenopolyCommands())

        server.pluginManager.registerEvents(MinenopolyEvents(), instance)
    }
}
