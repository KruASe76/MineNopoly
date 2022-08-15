package me.kruase.minenopoly

import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.entity.Player
import org.bukkit.ChatColor
import me.kruase.minenopoly.events.*
import me.kruase.minenopoly.util.hasAnyMark


class MinenopolyEvents : Listener {
    @EventHandler
    fun onChatMessage(event: AsyncPlayerChatEvent) {
        try {
            chat(event)
        } catch (e: IllegalStateException) {
            // "Unknown error" should never happen
            event.player.sendMessage("${ChatColor.RED}${e.message ?: "Unknown error"}")
        }
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        if (event.player !is Player) return
        Minenopoly.scoreboard.update(event.player as Player)
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

    @EventHandler
    fun onBankBreak(event: BlockBreakEvent) {
        if (event.block.location != Minenopoly.gameData?.bank?.location) return
        event.isCancelled = true
    }

    @EventHandler
    fun onGameItemConsume(event: PlayerItemConsumeEvent) {
        if (event.item.itemMeta?.persistentDataContainer?.hasAnyMark() != true) return
        event.isCancelled = true
    }

    @EventHandler
    fun onGameItemPlace(event: BlockPlaceEvent) {  // TODO: test this
        if (event.itemInHand.itemMeta?.persistentDataContainer?.hasAnyMark() != true) return
        event.isCancelled = true
    }
}
