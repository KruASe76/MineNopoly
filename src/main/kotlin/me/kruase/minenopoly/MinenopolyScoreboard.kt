package me.kruase.minenopoly

import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.RenderType
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.Server
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import me.kruase.minenopoly.util.*

object MinenopolyScoreboard {
    fun new(server: Server): Scoreboard =
        server.scoreboardManager!!.newScoreboard.apply {
            registerNewObjective(
                "money",
                "dummy",
                "${ChatColor.GREEN}MONEY",
                RenderType.INTEGER
            ).apply { displaySlot = DisplaySlot.SIDEBAR }
        }
}

fun Scoreboard.update(player: Player) {
    player.run {
        when (isInGame()) {
            true -> {
                getObjective("money")!!.getScore(name).apply { score = inventory.money }
                scoreboard = this@update
            }
            false -> {
                resetScores(name)
                scoreboard = server.scoreboardManager!!.mainScoreboard
            }
        }
    }
}
