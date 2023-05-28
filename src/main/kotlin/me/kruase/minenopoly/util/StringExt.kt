package me.kruase.minenopoly.util


fun String.multipleReplace(map: Map<String, String>): String {
    return map.entries.fold(this) {acc, (key, value) -> acc.replace(key, value)}
}
