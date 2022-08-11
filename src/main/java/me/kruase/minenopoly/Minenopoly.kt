package me.kruase.minenopoly

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Scoreboard


class Minenopoly : JavaPlugin() {
    companion object {
        lateinit var instance: Minenopoly
        lateinit var scoreboard: Scoreboard
        lateinit var mConfig: MinenopolyConfig

        var gameRunning: Boolean = false
        var gameData: MinenopolyGameData? = null
    }

    override fun onEnable() {
        instance = this
        scoreboard = MinenopolyScoreboard.new(server)
        mConfig = getMinenopolyConfig(instance)

        getCommand("monopoly")!!.setExecutor(MinenopolyCommands())

        server.pluginManager.registerEvents(MinenopolyEvents(), instance)
    }
}
