package me.kruase.minenopoly

import me.kruase.minenopoly.util.isInGame
import me.kruase.minenopoly.util.money
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.RenderType
import org.bukkit.scoreboard.Scoreboard
import net.md_5.bungee.api.ChatColor as CC

object MinenopolyScoreboard {
    fun new(server: Server): Scoreboard =
        server.scoreboardManager!!.newScoreboard.apply {
            registerNewObjective(
                "money",
                "dummy",
                "${CC.GREEN}MONEY",
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
