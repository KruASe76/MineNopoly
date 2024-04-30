package me.kruase.minenopoly

import me.kruase.minenopoly.events.chat
import me.kruase.minenopoly.util.*
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack
import net.md_5.bungee.api.ChatColor as CC


class MinenopolyEvents : Listener {
    @EventHandler
    fun onChatMessage(event: AsyncPlayerChatEvent) {
        try {
            chat(event)
        } catch (e: IllegalStateException) {
            event.player.sendMessage("${CC.RED}${e.message ?: "Unknown error"}")
            // "Unknown error" should never be output
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
            event.itemInHand.itemMeta?.persistentDataContainer?.run {
                hasMark("house") || hasMark("hotel")
            } != true
        ) return

        event.block.chunk.persistentDataContainer.addItemName(event.itemInHand, event.block.location)
    }

    @EventHandler
    fun onHouseDropItem(event: BlockDropItemEvent) {
        if (
            event.blockState.type != MSD.houseMaterial ||
            !event.block.chunk.persistentDataContainer.hasItemName(event.block.location)
        ) return
        event.items[0].itemStack.apply {
            itemMeta = itemMeta!!.apply {
                setDisplayName(
                    event.block.chunk.persistentDataContainer.getItemName(event.block.location)!! + CC.RESET
                )
                persistentDataContainer.addMark("house")
            }
        }
        event.block.chunk.persistentDataContainer.removeItemName(event.block.location)
    }

    @EventHandler
    fun onHotelDamage(event: BlockDamageEvent) {
        if (
            event.block.type != MSD.hotelMaterial ||
            !event.block.chunk.persistentDataContainer.hasItemName(event.block.location)
        ) return
        event.block.type = Material.AIR
        event.block.location.run {
            world!!.dropItemNaturally(
                this,
                ItemStack(MSD.hotelMaterial).apply {
                    itemMeta = itemMeta!!.apply {
                        setDisplayName(event.block.chunk.persistentDataContainer.getItemName(event.block.location)!!)
                        persistentDataContainer.addMark("hotel")
                    }
                }
            )
        }
        event.block.chunk.persistentDataContainer.removeItemName(event.block.location)
    }

    @EventHandler
    fun onHeadDamage(event: BlockDamageEvent) {
        if (
            event.block.type !in listOf(Material.PLAYER_HEAD, Material.PLAYER_WALL_HEAD) || !event.player.isInGame
        ) return

        event.block.breakNaturally()
    }

    @EventHandler
    fun onGameItemConsume(event: PlayerItemConsumeEvent) {
        if (event.item.itemMeta?.persistentDataContainer?.hasAnyMark() != true) return
        event.isCancelled = true
    }

    @EventHandler
    fun onGameItemPlace(event: BlockPlaceEvent) {
        if (event.itemInHand.itemMeta?.persistentDataContainer
                ?.run { hasAnyMark() && !hasMark("house") && !hasMark("hotel") } != true) return
        event.isCancelled = true
    }
}
