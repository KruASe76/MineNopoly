# MineNopoly

![](https://img.shields.io/badge/MINECRAFT-1.16.5+-966C4A?style=for-the-badge&labelColor=53AC56)
![](https://img.shields.io/badge/JAVA-1.8+-5283A2?style=for-the-badge&labelColor=E86F00)

A Spigot (Bukkit) Minecraft plugin that implements automated Monopoly bank into the game.


## Features

- Monopoly bank functionality using a `chest`, `barrel` or any `shulker_box` in which game items are placed and removed from
- US, UK and RU game localizations support
- Player balance tracking using a scoreboard
- _Chance_ and _Community chest_ cards randomization
- _Houses_ implementation using `sea_pickles` (can be placed up to four in a single block)
- _Hotels_ `lantern` blocks immediate breaking without tools
- `player_head` blocks immediate breaking (assumed to be tokens)
- A book with links to get game items and a list and descriptions of all game properties
- Built-in dice randomizer
- Bank block breaking protection; game items consuming/placement protection
- Anti-counterfeiting game items protection via [NBT data](https://minecraft.fandom.com/wiki/NBT_format)


## Usage

### How to play?

You just build a playing field, place a chest (can also be a barrel or a shulker box) somewhere and call `/mp start <chest coordinates>`!  
To get and remove game items use [chat entries](#valid-in-game-chat-entries) and the book you can get via `/mp book <localization>`.  
_Houses_ and _Hotels_ are meant to be placed on street cells; all other items are meant not to be used in any way except keeping in the inventory and transferring between the players.  
To finish the game return all the game items to the bank (and type `--` to remove them) and call `/mp finish`; if you can't return them one of the server operators (by default) should call `/mp finish forced`.

### Commands

`/minenopoly` is the main plugin command, which has the alias `/mp`.

| Command              | Description                                                                   |
|----------------------|-------------------------------------------------------------------------------|
| `/mp help [command]` | Show help for given command, for available commands otherwise                 |
| `/mp book <loc>`     | Get the book to use during the game                                           |
| `/mp start <block>`  | Start the game (chat tracking, scoreboard, etc.) with given block as the bank |
| `/mp finish`         | Finish the game                                                               |
| `/mp finish forced`  | Finish even if not all game items have been returned                          |
| `/mp reload`         | Reload config                                                                 |
| `/mp get <args>`     | Auxiliary command used when clicking on links in the book                     |

### Valid in-game chat entries

_Note:_ Only available during the game.

| Chat entry  | Description                                                                    |
|-------------|--------------------------------------------------------------------------------|
| `+<number>` | Place given amount of game money into the bank                                 |
| `-<number>` | Remove given amount of game money from the bank (with change if needed)        |
| `--`        | Remove all game items from the bank (used action cards, sold properties, etc.) |
| `?`         | Roll the dice (display two random numbers from 1 to 6)                         |


## Configuration ([default](/src/main/resources/config.yml))

- Game money items (also their denominations)
- Plugin messages
  - info
  - error
  - help


## Permissions

| Permission node            | Default | Description                                                             |
|----------------------------|---------|-------------------------------------------------------------------------|
| `minenopoly.help`          | true    | Allows to use `/mp help` (lists only available commands)                |
| `minenopoly.get`           | true    | Allows to use `/mp get` and chat entries (allows to play basically)     |
| `minenopoly.book`          | true    | Allows to use `/mp book`                                                |
| `minenopoly.start`         | true    | Allows to use `/mp start`                                               |
| `minenopoly.finish`        | true    | Allows to use `/mp finish` (without `forced` argument)                  |
| `minenopoly.finish.forced` | op      | Allows to use `/mp finish forced`                                       |
| `minenopoly.reload`        | op      | Allows to use `/mp reload`                                              |
| `minenopoly.admin`         | op      | Refers to `minenopoly.reload` and `minenopoly.finish.forced` by default |


## Game field

Here is a [litematica](https://github.com/KruASe76/MineNopoly/raw/main/additions/field.litematic) of the game field designed by me, however, you can always build your own!


## Reference

- [PlugManX](https://github.com/TheBlackEntity/PlugMan) (also used during development)
  - `README.md` structure
  - Some code
- [Monopoly Wiki](https://monopoly.fandom.com/wiki/Main_Page)
  - Property names, _Chance_ and _Community chest_ cards for US and UK editions
- [Wikibooks (Monopoly properties reference)](https://en.wikibooks.org/wiki/Monopoly/Properties_reference)
  - Property costs and rents


## Special thanks to:

- [Legitimoose](https://www.youtube.com/c/Legitimoose) for amazing Paper (Bukkit) plugin (in Kotlin) project setup [tutorial](https://youtu.be/5DBJcz0ceaw)
- [BeBr0](https://www.youtube.com/c/BeBr0) for Spigot (Bukkit) plugin development [tutorial [ru]](https://youtube.com/playlist?list=PLlLq-eYkh0bB_uyZN4NdzkxLBs9glZmIT) with very clear API explanation

## Copyright

All the information about the original game used to develop this project was taken from open sources (mostly [Monopoly fandom wiki](https://monopoly.fandom.com/wiki/Main_Page)) and my personal experience.

The project itself is distributed under [GNU GPLv3](./LICENSE).
