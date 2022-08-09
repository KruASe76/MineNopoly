package me.kruase.minenopoly

import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.RenderType
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.Server
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import me.kruase.minenopoly.util.money

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
    when (val money: Int = player.inventory.money) {
        0 -> {
            resetScores(player.name)
            player.scoreboard = player.server.scoreboardManager!!.mainScoreboard
        }
        else -> {
            getObjective("money")!!.getScore(player.name).apply { score = money }
            player.scoreboard = this
        }
    }
}
