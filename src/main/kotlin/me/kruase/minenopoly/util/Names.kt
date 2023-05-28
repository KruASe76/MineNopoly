package me.kruase.minenopoly.util

import net.md_5.bungee.api.ChatColor as CC


fun coloredName(name: String, outerStyle: String = ""): String {
    return name.let {
        if (it.startsWith(CC.COLOR_CHAR)) "$it${CC.RESET}$outerStyle"
        else "${CC.YELLOW}$it${CC.RESET}$outerStyle"
    }
}


fun asCurrency(cost: Int, outerStyle: String = ""): String {
    return "${CC.RESET}${CC.GREEN}${CC.STRIKETHROUGH}M" +
            "${CC.RESET}${CC.GREEN}$cost" +
            "${CC.RESET}$outerStyle"
}
