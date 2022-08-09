package me.kruase.minenopoly

import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.entity.Player
import me.kruase.minenopoly.events.*


class MinenopolyEvents : Listener {
    @EventHandler
    fun onChatMessage(event: AsyncPlayerChatEvent) {
        chat(event)
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.whoClicked !is Player) return
        Minenopoly.instance.server.scheduler.runTask(
            Minenopoly.instance, Runnable { Minenopoly.scoreboard.update(event.whoClicked as Player) }
        )
    }

    @EventHandler
    fun onItemDrop(event: PlayerDropItemEvent) {
        Minenopoly.instance.server.scheduler.runTask(
            Minenopoly.instance, Runnable { Minenopoly.scoreboard.update(event.player) }
        )
    }

    @EventHandler
    fun onItemPickup(event: EntityPickupItemEvent) {
        if (event.entity !is Player) return
        Minenopoly.instance.server.scheduler.runTask(
            Minenopoly.instance, Runnable { Minenopoly.scoreboard.update(event.entity as Player) }
        )
    }
}
