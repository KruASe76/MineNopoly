package me.kruase.minenopoly

import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.util.isInGame
import me.kruase.minenopoly.util.money
import me.kruase.minenopoly.util.multipleReplace
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.RenderType
import org.bukkit.scoreboard.Scoreboard
import net.md_5.bungee.api.ChatColor as CC


object MinenopolyScoreboard {
    fun new(server: Server): Scoreboard =
        server.scoreboardManager!!.newScoreboard
            .apply {
                registerNewObjective(
                    "money",
                    Criteria.DUMMY,
                    "${CC.GREEN}MONEY",
                    RenderType.INTEGER
                )
                    .apply { displaySlot = DisplaySlot.SIDEBAR }
            }
}

fun Scoreboard.update(player: Player) {
    player.run {
        val entryName =
            (if (userConfig.scoreboard.useDisplayName) displayName else name)
                .let {
                    if (it in userConfig.scoreboard.nameReplaceExclusions)
                        it
                    else
                        it.multipleReplace(userConfig.scoreboard.nameReplaceMap, true)
                }

        when (isInGame()) {
            true -> {
                getObjective("money")!!
                    .getScore(entryName)
                    .apply { score = inventory.money }

                scoreboard = this@update
            }
            false -> {
                resetScores(entryName)

                scoreboard = server.scoreboardManager!!.mainScoreboard
            }
        }
    }
}
