package me.kruase.minenopoly

import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.entity.Player
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.ChatColor
import me.kruase.minenopoly.events.*
import me.kruase.minenopoly.util.*


class MinenopolyEvents : Listener {
    @EventHandler
    fun onChatMessage(event: AsyncPlayerChatEvent) {
        try {
            chat(event)
        } catch (e: IllegalStateException) {
            // "Unknown error" should never occur
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
    fun onBuildingPlace(event: BlockPlaceEvent) {
        if (
            event.itemInHand.itemMeta?.persistentDataContainer?.run { hasMark("house") || hasMark("hotel") } != true ||
            !event.player.isInGame()
        ) return
        event.block.chunk.persistentDataContainer.addItemName(event.itemInHand, event.block.location)
    }

    @EventHandler
    fun onHotelDamage(event: BlockDamageEvent) {
        if (
            event.block.type != Material.LANTERN ||
            !event.block.chunk.persistentDataContainer.hasItemName(event.block.location)
        ) return
        event.block.type = Material.AIR
        event.block.location.run {
            world!!.dropItemNaturally(
                this,
                ItemStack(Material.LANTERN).apply { itemMeta = itemMeta!!
                    .apply {
                        setDisplayName(event.block.chunk.persistentDataContainer.getItemName(event.block.location)!!)
                        persistentDataContainer.addMark("hotel")
                    }
                }
            )
        }
        event.block.chunk.persistentDataContainer.removeItemName(event.block.location)
    }

    @EventHandler
    fun onHouseDropItem(event: BlockDropItemEvent) {
        if (
            event.blockState.block.type != Material.SEA_PICKLE ||
            !event.block.chunk.persistentDataContainer.hasItemName(event.block.location)
        ) return
        event.items[0].itemStack.apply { itemMeta = itemMeta!!
            .apply {
                setDisplayName(event.block.chunk.persistentDataContainer.getItemName(event.block.location)!!)
                persistentDataContainer.addMark("house")
            }
        }
        event.block.chunk.persistentDataContainer.removeItemName(event.block.location)
    }

    @EventHandler
    fun onGameItemConsume(event: PlayerItemConsumeEvent) {
        if (event.item.itemMeta?.persistentDataContainer?.hasAnyMark() != true) return
        event.isCancelled = true
    }

    @EventHandler
    fun onGameItemPlace(event: BlockPlaceEvent) {  // TODO: test this
        if (event.itemInHand.itemMeta?.persistentDataContainer
                ?.run { hasAnyMark() && !hasMark("house") && !hasMark("hotel") } != true) return
        event.isCancelled = true
    }
}
