# mine-nopoly

A Spigot (Bukkit) Minecraft plugin that implements automated Monopoly bank into the game.


## Features [WIP]

- [ ] Monopoly bank functionality using a `chest` or a `barrel` in which game money items are placed and removed from
- [ ] _Chance_ and _Community chest_ cards random cycle (shuffled card deck imitation)
- [x] Player balance tracking using a scoreboard
- [ ] _Houses_ and _Hotels_ implementation using candles (`green_candle` as a _House_, `red_candle` as a _Hotel_)
- [ ] A book with links to get game items and a list and descriptions of all game properties
- [ ] Built-in dice randomizer
- [x] Anti-counterfeiting game money items (default: `flint`, `coal`, `quartz`, `lapis_lazuli`, `iron_nugget`, `gold_nugget`, `emerald`) protection via custom (unreachable in survival) item names
- [ ] Game money tracking in player inventories using a log file with player balance changes


## Usage

### Commands
`/monopoly` is the main plugin command, which has the alias `/mp`.

| Command              | Description                                                                  |
|----------------------|------------------------------------------------------------------------------|
| `/mp help [command]` | Show help for given command, for available commands otherwise                |
| `/mp start <block>`  | Start the game (chat tracking, scoreboard, etc) with given block as the bank |
| `/mp finish`         | Finish the game and save the log file                                        |
| `/mp finish forced`  | Finish even if not all game items have been returned                         |
| `/mp book`           | Get the book to use during the game                                          |
| `/mp reload`         | Reload config                                                                |
| `/mp get <args>`     | Auxiliary command used when clicking on links in the book                    |

### Valid in-game chat entries
_Note:_ Only available during the game.

| Chat entry  | Description                                                             |
|-------------|-------------------------------------------------------------------------|
| `+<number>` | Place given amount of game money into the bank                          |
| `-<number>` | Remove given amount of game money from the bank (with change if needed) |
| `--`        | Remove all items from the bank (used _Chance_ cards, sold properties)   |
| `?`         | Roll the dice (two random numbers from 1 to 6)                          |


## Configuration ([default](/src/main/resources/config.yml))

- Game money items (it is also possible to specify denominations)
- Plugin messages [WIP]


## Permissions
| Permission node            | Default | Description                                                            |
|----------------------------|---------|------------------------------------------------------------------------|
| `minenopoly.help`          | true    | Allows to use `/mp help` (lists only available commands)               |
| `minenopoly.get`           | true    | Allows to use `/mp get` and chat entries (allows to play basically)    |
| `minenopoly.start`         | true    | Allows to use `/mp start`                                              |
| `minenopoly.finish`        | true    | Allows to use `/mp finish` (without `forced` argument)                 |
| `minenopoly.finish.forced` | op      | Allows to use `/mp finish forced`                                      |
| `minenopoly.book`          | true    | Allows to use `/mp book`                                               |
| `minenopoly.reload`        | op      | Allows to use `/mp reload`                                             |
| `minenopoly.admin`         | op      | Refers to `minenopoly.reload` and `minenopoly.finish.forced` by default |


## Game field

Here is a [litematica](https://github.com/KruASe76/mine-nopoly/raw/main/additions/Monopoly_Field.litematic) of the game field designed by me, however, you can build your own!


## Reference

- [PlugManX](https://github.com/TheBlackEntity/PlugMan) (also used during development)
    - `README.md` structure
    - Some code


## Special thanks to:

- [Legitimoose](https://www.youtube.com/c/Legitimoose) for amazing Paper (Bukkit) plugin (on Kotlin) project setup [tutorial](https://youtu.be/5DBJcz0ceaw)
- [This portuguese guy](https://www.youtube.com/user/ReiDaViadagi) for showing me that I should add `kotlin-stdlib` to `plugin.yml`'s `libraries` section
- [BeBr0](https://www.youtube.com/c/BeBr0) for Spigot (Bukkit) plugin development [tutorial [ru]](https://youtube.com/playlist?list=PLlLq-eYkh0bB_uyZN4NdzkxLBs9glZmIT) with very clear API explanation
