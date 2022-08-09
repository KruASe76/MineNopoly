package me.kruase.minenopoly

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.block.Container


class Minenopoly : JavaPlugin() {
    companion object {
        lateinit var instance: Minenopoly
        lateinit var scoreboard: Scoreboard
        lateinit var mConfig: MinenopolyConfig

        var gameRunning: Boolean = false

        var bank: Container? = null
        var moneyInGame: Int? = null
        var chancesInGame: Int? = null
        var communityChestsInGame: Int? = null
        var propertiesInGame: Int? = null
        var housesInGame: Int? = null
        var hotelsInGame: Int? = null
    }

    override fun onEnable() {
        instance = this
        scoreboard = MinenopolyScoreboard.new(server)
        mConfig = getMinenopolyConfig(instance)

        getCommand("monopoly")!!.setExecutor(MinenopolyCommands())

        server.pluginManager.registerEvents(MinenopolyEvents(), instance)
    }
}
