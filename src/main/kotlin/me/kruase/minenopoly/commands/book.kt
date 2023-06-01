package me.kruase.minenopoly.commands

import me.kruase.minenopoly.MSD
import me.kruase.minenopoly.Minenopoly.Companion.instance
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.util.hasPluginPermission
import me.kruase.minenopoly.util.multipleReplace
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta
import java.awt.Color
import net.md_5.bungee.api.ChatColor as CC


private val colorFix = mapOf(
    Color(0x573114) to CC.DARK_RED,
    Color(0x1983C4) to CC.DARK_AQUA,
    Color(0xD35D89) to CC.RED,
    Color(0xDE5800) to CC.GOLD,
    Color(0xA51515) to CC.DARK_RED,
    Color(0xF2AB0B) to CC.YELLOW,
    Color(0x55A30C) to CC.GREEN,
    Color(0x212388) to CC.DARK_BLUE,
).map { (key, value) -> CC.of(key).toString() to value.toString() }.toMap()


fun book(player: Player, args: Array<out String>) {
    if (!player.hasPluginPermission("book")) throw UnsupportedOperationException()

    if (args.isEmpty() || args[0] !in listOf("us", "uk", "ru")) throw IllegalArgumentException()


    val loc = MSD.loc[args[0]]!!

    ItemStack(Material.WRITTEN_BOOK).apply {
        itemMeta = (itemMeta as BookMeta).apply {
            setDisplayName("${CC.GREEN}${loc.bookName}${CC.RESET}")
            lore = loc.bookItemLore

            title = loc.bookName
            author = loc.bookAuthor

            spigot().addPage(
                ComponentBuilder("")
                    .append(
                        ComponentBuilder(loc.chanceName)
                            .event(ClickEvent(ClickEvent.Action.RUN_COMMAND, "/mn get ${loc.literal} chance"))
                            .event(HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(loc.bookHoverText)))
                            .create()
                    )
                    .append("\n")
                    .append(
                        ComponentBuilder(loc.communityChestName)
                            .event(ClickEvent(ClickEvent.Action.RUN_COMMAND, "/mn get ${loc.literal} community_chest"))
                            .event(HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(loc.bookHoverText)))
                            .create()
                    )
                    .append("\n\n")
                    .append(
                        ComponentBuilder(loc.houseName)
                            .event(ClickEvent(ClickEvent.Action.RUN_COMMAND, "/mn get ${loc.literal} house"))
                            .event(HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(loc.bookHoverText)))
                            .create()
                    )
                    .append("\n")
                    .append(
                        ComponentBuilder(loc.hotelName)
                            .event(ClickEvent(ClickEvent.Action.RUN_COMMAND, "/mn get ${loc.literal} hotel"))
                            .event(HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(loc.bookHoverText)))
                            .create()
                    )
                    .append(  // event reset is needed to fix price list title becoming hoverable and clickable
                        ComponentBuilder("\n\n\n")
                            .event(ClickEvent(ClickEvent.Action.RUN_COMMAND, ""))
                            .event(HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("")))
                            .create()
                    )
                    .append(loc.bookPriceList)
                    .create()
            )

            spigot().addPage(
                *loc.properties.map { (type, property) ->
                    ComponentBuilder(
                        "${property.name}\n\n${property.description.joinToString("\n")}".multipleReplace(colorFix)
                    )
                        .event(
                            ClickEvent(
                                ClickEvent.Action.RUN_COMMAND,
                                "/mn get ${loc.literal} property ${type.replace(".", " ")}"
                            )
                        )
                        .event(HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(loc.bookHoverText)))
                        .create()
                }.toTypedArray()
            )
        }
    }.let { itemStack ->
        player.inventory.addItem(itemStack).values.forEach {
            instance.server.scheduler.callSyncMethod(instance) {
                player.location.world?.dropItemNaturally(player.location, it)
                    ?: throw IllegalStateException(
                        userConfig.messages.error["unable-to-add-item"] ?: "Error: unable-to-add-item"
                    )
            }
        }
    }
}
