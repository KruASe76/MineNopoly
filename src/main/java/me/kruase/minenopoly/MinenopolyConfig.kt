package me.kruase.minenopoly

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.Material
import org.bukkit.plugin.Plugin
import java.io.File


object MinenopolyConfig {
    private val config: FileConfiguration = Minenopoly.instance.config

    // "materials" is more API-related name, when "item" is more user-friendly
    object Materials {
        val money: Map<Int, Material> = config.getConfigurationSection("items.money")!!
            .getKeys(false).sortedByDescending { it.toInt() }.associate {
                it.toInt() to Material.matchMaterial(config.getString("items.money.${it}")!!)!!
            }
        val chance: Material = Material.matchMaterial(config.getString("items.chance")!!)!!
        val communityChest: Material = Material.matchMaterial(config.getString("items.community-chest")!!)!!
    }

    object Messages {
        val help: Map<String, String> = config.getConfigurationSection("messages.help")!!
            .getKeys(false).associateWith { config.getString("messages.help.${it}")!! }
    }
}

fun ensureConfigIsValid(plugin: Plugin) {
    try {  // initializing all MinenopolyConfig properties
        MinenopolyConfig.Materials
        MinenopolyConfig.Messages
    } catch (e: ExceptionInInitializerError) {
        plugin.logger.severe("Invalid Minenopoly config detected! Creating a new one (default)...")
        File(plugin.dataFolder, "config.yml").renameTo(
            File(plugin.dataFolder, "config.yml.old-${System.currentTimeMillis()}")
        )
        plugin.saveDefaultConfig()
        plugin.logger.info("New (default) config created!")
    }
}
