package me.kruase.minenopoly

import me.kruase.minenopoly.util.asCurrency
import org.bukkit.Material
import java.awt.Color
import net.md_5.bungee.api.ChatColor as CC


private val nameToColor = mapOf(
    "brown" to Color(0x573114),
    "cyan" to Color(0x1983C4),
    "pink" to Color(0xD35D89),
    "orange" to Color(0xDE5800),
    "red" to Color(0xA51515),
    "yellow" to Color(0xF2AB0B),
    "green" to Color(0x55A30C),
    "blue" to Color(0x212388),
)


@Suppress("unused")
typealias MSD = MinenopolyStaticData
typealias LPT = LogicalPropertyType


object MinenopolyStaticData {
    const val totalHouses = 32
    const val totalHotels = 12

    val propertyPrices = listOf(
        listOf(
            listOf( 60,   50,   2,   4,   10,   30,   90,   160,   250,   30,   33  ),
            listOf( 60,   50,   4,   8,   20,   60,   180,  320,   450,   30,   33  ),
            listOf( 100,  50,   6,   12,  30,   90,   270,  400,   550,   50,   55  ),
            listOf( 100,  50,   6,   12,  30,   90,   270,  400,   550,   50,   55  ),
            listOf( 120,  50,   8,   16,  40,   100,  300,  450,   600,   60,   66  ),
            listOf( 140,  100,  10,  20,  50,   150,  450,  625,   750,   70,   77  ),
            listOf( 140,  100,  10,  20,  50,   150,  450,  625,   750,   70,   77  ),
            listOf( 160,  100,  12,  24,  60,   180,  500,  700,   900,   80,   88  ),
            listOf( 180,  100,  14,  28,  70,   200,  550,  750,   950,   90,   99  ),
            listOf( 180,  100,  14,  28,  70,   200,  550,  750,   950,   90,   99  ),
            listOf( 200,  100,  16,  32,  80,   220,  600,  800,   1000,  100,  110 ),
            listOf( 220,  150,  18,  36,  90,   250,  700,  875,   1050,  110,  121 ),
            listOf( 220,  150,  18,  36,  90,   250,  700,  875,   1050,  110,  121 ),
            listOf( 240,  150,  20,  40,  100,  300,  750,  925,   1100,  120,  132 ),
            listOf( 260,  150,  22,  44,  110,  330,  800,  975,   1150,  130,  143 ),
            listOf( 260,  150,  22,  44,  110,  330,  800,  975,   1150,  130,  143 ),
            listOf( 280,  150,  24,  48,  120,  360,  850,  1025,  1200,  140,  154 ),
            listOf( 300,  200,  26,  52,  130,  390,  900,  1100,  1275,  150,  165 ),
            listOf( 300,  200,  26,  52,  130,  390,  900,  1100,  1275,  150,  165 ),
            listOf( 320,  200,  28,  56,  150,  450,  1000, 1200,  1400,  160,  176 ),
            listOf( 350,  200,  35,  70,  175,  500,  1100, 1300,  1500,  175,  193 ),
            listOf( 400,  200,  50,  100, 200,  600,  1400, 1700,  2000,  200,  220 ),
        ),
        List(4) { listOf(200, 25, 50, 100, 200, 100, 110) },
        List(2) { listOf(150, 4, 10, 75, 83) },
    ).flatten()


    val houseMaterial = Material.SEA_PICKLE
    val hotelMaterial = Material.LANTERN


    val propertyTypes: List<String> = listOf(
        "street.brown.1", "street.brown.2",
        "street.cyan.1", "street.cyan.2", "street.cyan.3",
        "street.pink.1", "street.pink.2", "street.pink.3",
        "street.orange.1", "street.orange.2", "street.orange.3",
        "street.red.1", "street.red.2", "street.red.3",
        "street.yellow.1", "street.yellow.2", "street.yellow.3",
        "street.green.1", "street.green.2", "street.green.3",
        "street.blue.1", "street.blue.2",
        "railroad.1", "railroad.2", "railroad.3", "railroad.4",
        "utility.electricity", "utility.water",
    )


    val loc: Map<String, Localization> = mapOf(
        "us" to Localization(
            literal = "us",

            chanceNameRaw = "Chance",
            communityChestNameRaw = "Community chest",
            houseNameRaw = "House",
            hotelNameRaw = "Hotel",

            bookName = "Monopoly player's book",
            bookHoverText = "Click to get item",
            bookItemLoreRaw = listOf(
                "Contains links to get game-related items",
                "and information about properties"
            ),
            bookFirstPageNames = listOf(
                "PRICE LIST" to 0,
                "GAME START" to 13,
                "GO PASS" to 21,
                "INCOME TAX" to 14,
                "LUXURY TAX" to 13,
                "LEAVE THE JAIL" to 3,
                "OR DOUBLE" to 27,
            ),

            propertyNamesRaw = listOf(
                "Mediterranean Avenue", "Baltic Avenue\n",
                "Oriental Avenue\n", "Vermont Avenue\n", "Connecticut Avenue\n",
                "St. Charles Place\n", "States Avenue\n", "Virginia Avenue\n",
                "St. James Place\n", "Tennessee Avenue\n", "New York Avenue\n",
                "Kentucky Avenue\n", "Indiana Avenue\n", "Illinois Avenue\n",
                "Atlantic Avenue\n", "Ventnor Avenue\n", "Marvin Gardens\n",
                "Pacific Avenue\n", "North Carolina Avenue", "Pennsylvania Avenue",
                "Park Place\n", "Boardwalk\n",
                "Reading Railroad\n", "Pennsylvania Railroad", "B. & O. Railroad\n", "Short Line\n",
                "Electric Company\n", "Water Works\n",
            ),

            chancesRaw = listOf(
                listOf(
                    "Advance to GO",
                    "(Collect ${asCurrency(200, Style.lore)})",
                ),
                listOf(
                    "Advance to Illinois Avenue",
                    "If you pass GO, collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Advance to St. Charles Place",
                    "If you pass GO, collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Advance token to the nearest Utility",
                    "If unowned, you may buy it from the Bank.",
                    "If owned, throw dice and pay owner a total",
                    "10 (ten) times the amount thrown.",
                ),
                listOf(
                    "Advance to the nearest Railroad",
                    "If unowned, you may buy it from the Bank.",
                    "If owned, pay owner twice the rental",
                    "to which they are otherwise entitled.",
                ),
                listOf(
                    "Bank pays you dividend of ${asCurrency(50, Style.loreTitle)}",
                ),
                listOf(
                    "Get out of Jail for free",
                    "This card may be kept until needed,",
                    "or traded/sold.",
                ),
                listOf(
                    "Go back three spaces",
                ),
                listOf(
                    "Go directly to Jail",
                    "Do not pass GO, do not collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Make general repairs on all your property",
                    "For each house pay ${asCurrency(25, Style.lore)},",
                    "for each hotel pay ${asCurrency(100, Style.lore)}.",
                ),
                listOf(
                    "Speeding fine ${asCurrency(15, Style.loreTitle)}",
                ),
                listOf(
                    "Take a ride to King’s Cross Station",
                    "If you pass GO, collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Take a walk on the board walk",
                    "Advance token to Mayfair.",
                ),
                listOf(
                    "You have been elected Chairman of the Board",
                    "Pay each player ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "Your building loan matures",
                    "Receive ${asCurrency(150, Style.lore)}.",
                ),
                listOf(
                    "You have won a crossword competition",
                    "Collect ${asCurrency(100, Style.lore)}.",
                )
            ),

            communityChestsRaw = listOf(
                listOf(
                    "Advance to GO",
                    "(Collect ${asCurrency(200, Style.lore)})",
                ),
                listOf(
                    "Bank error in your favor",
                    "Collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Doctor's fees",
                    "Pay ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "From sale of stock you get ${asCurrency(50, Style.loreTitle)}",
                ),
                listOf(
                    "Get out of Jail for free",
                    "This card may be kept until needed,",
                    "or traded/sold.",
                ),
                listOf(
                    "Go directly to Jail",
                    "Do not pass GO, do not collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Holiday fund matures",
                    "Receive ${asCurrency(100, Style.lore)}.",
                ),
                listOf(
                    "Income tax refund",
                    "Collect ${asCurrency(20, Style.lore)}.",
                ),
                listOf(
                    "It is your birthday",
                    "Collect ${asCurrency(10, Style.lore)} from every player.",
                ),
                listOf(
                    "Life insurance matures",
                    "Collect ${asCurrency(100, Style.lore)}.",
                ),
                listOf(
                    "Hospital fees",
                    "Pay ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "School fees",
                    "Pay ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "Receive ${asCurrency(25, Style.loreTitle)} consultancy fee",
                ),
                listOf(
                    "You are assessed for street repairs",
                    "Pay ${asCurrency(40, Style.lore)} per house and ${asCurrency(115, Style.lore)} per hotel you own.",
                ),
                listOf(
                    "You have won second prize in a beauty contest",
                    "Collect ${asCurrency(10, Style.lore)}.",
                ),
                listOf(
                    "You inherit ${asCurrency(100, Style.loreTitle)}",
                )
            ),
        ),

        "uk" to Localization(
            literal = "uk",

            chanceNameRaw = "Chance",
            communityChestNameRaw = "Community chest",
            houseNameRaw = "House",
            hotelNameRaw = "Hotel",

            bookName = "Monopoly player's book",
            bookHoverText = "Click to get item",
            bookItemLoreRaw = listOf(
                "Contains links to get game-related items",
                "and information about properties"
            ),
            bookFirstPageNames = listOf(
                "PRICE LIST" to 0,
                "GAME START" to 13,
                "GO PASS" to 21,
                "INCOME TAX" to 14,
                "LUXURY TAX" to 13,
                "LEAVE THE JAIL" to 3,
                "OR DOUBLE" to 27,
            ),

            propertyNamesRaw = listOf(
                "Old Kent Road\n", "Whitechapel Road\n",
                "The Angel Islington\n", "Euston Road\n", "Pentonville Road\n",
                "Pall Mall\n", "Whitehall\n", "Northumberland Avenue",
                "Bow Street\n", "Marlborough Street\n", "Vine Street\n",
                "Strand\n", "Fleet Street\n", "Trafalgar Square\n",
                "Leicester Square\n", "Coventry Street\n", "Piccadilly\n",
                "Regent Street\n", "Oxford Street\n", "Bond Street\n",
                "Park Lane\n", "Mayfair\n",
                "King's Cross Station\n", "Marylebone Station\n", "Fenchurch Street Station", "Liverpool Street Station",
                "Electric Company\n", "Water Works\n",
            ),

            chancesRaw = listOf(
                listOf(
                    "Advance to GO",
                    "(Collect ${asCurrency(200, Style.lore)})",
                ),
                listOf(
                    "Advance to Trafalgar Square",
                    "If you pass GO, collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Advance to Pall Mall",
                    "If you pass GO, collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Advance token to the nearest Utility",
                    "If unowned, you may buy it from the Bank.",
                    "If owned, throw dice and pay owner a total",
                    "10 (ten) times the amount thrown.",
                ),
                listOf(
                    "Advance to the nearest Railroad",
                    "If unowned, you may buy it from the Bank.",
                    "If owned, pay owner twice the rental",
                    "to which they are otherwise entitled.",
                ),
                listOf(
                    "Bank pays you dividend of ${asCurrency(50, Style.loreTitle)}",
                ),
                listOf(
                    "Get out of Jail for free",
                    "This card may be kept until needed,",
                    "or traded/sold.",
                ),
                listOf(
                    "Go back three spaces",
                ),
                listOf(
                    "Go directly to Jail",
                    "Do not pass GO, do not collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Make general repairs on all your property",
                    "For each house pay ${asCurrency(25, Style.lore)},",
                    "for each hotel pay ${asCurrency(100, Style.lore)}.",
                ),
                listOf(
                    "Speeding fine ${asCurrency(15, Style.loreTitle)}",
                ),
                listOf(
                    "Take a trip to Reading Railroad",
                    "If you pass GO, collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Take a walk on the Boardwalk",
                    "Advance token to Boardwalk.",
                ),
                listOf(
                    "You have been elected Chairman of the Board",
                    "Pay each player ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "Your building loan matures",
                    "Receive ${asCurrency(150, Style.lore)}.",
                ),
                listOf(
                    "You have won a crossword competition",
                    "Collect ${asCurrency(100, Style.lore)}.",
                )
            ),

            communityChestsRaw = listOf(
                listOf(
                    "Advance to GO",
                    "(Collect ${asCurrency(200, Style.lore)})",
                ),
                listOf(
                    "Bank error in your favor",
                    "Collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Doctor's fees",
                    "Pay ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "From sale of stock you get ${asCurrency(50, Style.loreTitle)}",
                ),
                listOf(
                    "Get out of Jail for free",
                    "This card may be kept until needed,",
                    "or traded/sold.",
                ),
                listOf(
                    "Go directly to Jail",
                    "Do not pass GO, do not collect ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Holiday fund matures",
                    "Receive ${asCurrency(100, Style.lore)}.",
                ),
                listOf(
                    "Income tax refund",
                    "Collect ${asCurrency(20, Style.lore)}.",
                ),
                listOf(
                    "It is your birthday",
                    "Collect ${asCurrency(10, Style.lore)} from every player.",
                ),
                listOf(
                    "Life insurance matures",
                    "Collect ${asCurrency(100, Style.lore)}.",
                ),
                listOf(
                    "Hospital fees",
                    "Pay ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "School fees",
                    "Pay ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "Receive ${asCurrency(25, Style.loreTitle)} consultancy fee",
                ),
                listOf(
                    "You are assessed for street repairs",
                    "Pay ${asCurrency(40, Style.lore)} per house and ${asCurrency(115, Style.lore)} per hotel you own.",
                ),
                listOf(
                    "You have won second prize in a beauty contest",
                    "Collect ${asCurrency(10, Style.lore)}.",
                ),
                listOf(
                    "You inherit ${asCurrency(100, Style.loreTitle)}",
                )
            ),
        ),

        "ru" to Localization(
            literal = "ru",

            chanceNameRaw = "Шанс",
            communityChestNameRaw = "Общественная казна",
            houseNameRaw = "Дом",
            hotelNameRaw = "Отель",

            bookName = "Книга игрока в Монополию",
            bookHoverText = "Нажмите, чтобы получить предмет",
            bookItemLoreRaw = listOf(
                "Содержит ссылки на получение игровых предметов",
                "и информацию о собственностях"
            ),
            bookFirstPageNames = listOf(
                "ПРАЙС-ЛИСТ" to 0,
                "НАЧАЛО ИГРЫ" to 9,
                "ПОЛЕ ВПЕРЁД" to 6,
                "ПОДОХОД. НАЛОГ" to 2,
                "СВЕРХНАЛОГ" to 12,
                "ТЮРЬМА" to 23,
                "ИЛИ ДУБЛЬ" to 26,
            ),

            propertyNamesRaw = listOf(
                "Нагатинская улица\n", "Житная улица\n",
                "Первая Парковая улица", "Улица Огарёва\n", "Варшавское шоссе\n",
                "Улица Полянка\n", "Улица Сретенка\n", "Ростовская набережная",
                "Рязанский проспект", "Улица Вавилова\n", "Рублёвское шоссе\n",
                "Улица Тверская\n", "Пушкинская площадь", "Площадь Маяковского",
                "Улица Грузинский Вал", "Новинский бульвар\n", "Смоленская площадь",
                "Улица Щусева\n", "Гоголевский бульвар", "Кутузовский проспект",
                "Улица Малая Бронная", "Улица Арбат\n",
                "Рижская железная дорога", "Курская железная дорога", "Казанская железная дорога", "Ленинградская железная дорога",
                "Электростанция\n", "Водоканал\n",
            ),

            chancesRaw = listOf(
                listOf(
                    "Иди на поле ВПЕРЁД",
                    "(Получи ${asCurrency(200, Style.lore)})",
                ),
                listOf(
                    "Отправляйся на Площадь Маяковского",
                    "Если проходишь поле ВПЕРЁД, получи ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Отправляйся на улицу Полянка",
                    "Если проходишь поле ВПЕРЁД, получи ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Отправляйся на ближайшее Коммунальное Предприятие",
                    "Если оно никому не принадлежит, можешь заплатить в Банк и купить его.",
                    "Если оно является собственностью другого игрока, бросай кубики и",
                    "заплати владельцу ренту, равную сумме выпавших очков, умноженных на 10.",
                ),
                listOf(
                    "Отправляйся на ближайшую Станцию",
                    "Если она никому не принадлежит, можешь заплатить в Банк и купить её.",
                    "Если она является собственностью другого игрока, заплати владельцу",
                    "ренту в два раза больше обычной.",
                ),
                listOf(
                    "Банк выплачивает тебе дивиденд",
                    "в размере ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "Выйти из Тюрьмы бесплатно",
                    "Эту карточку можно сохранить",
                    "на будущее или обменять.",
                ),
                listOf(
                    "Вернись на три поля назад",
                ),
                listOf(
                    "Ты арестован!",
                    "Отправляйся сразу в тюрьму.",
                    "Ты не проходишь поле ВПЕРЁД",
                    "и не получаешь ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Капитальный ремонт всей твоей собственности",
                    "Заплати ${asCurrency(25, Style.lore)} за каждый дом и ${asCurrency(100, Style.lore)} за каждый отель.",
                ),
                listOf(
                    "Штраф за превышение скорости ${asCurrency(15, Style.loreTitle)}",
                ),
                listOf(
                    "Прокатись по Рижской железной дороге",
                    "Если проходишь поле ВПЕРЁД, получи ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Отправляйся на улицу Арбат",
                ),
                listOf(
                    "Тебя избрали председателем совета директоров",
                    "Заплати каждому игроку по ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "Ссуда на строительство",
                    "Получи ${asCurrency(150, Style.lore)}.",
                ),
                listOf(
                    "Ты выиграл чемпионат по кроссвордам!",
                    "Получи ${asCurrency(100, Style.lore)}.",
                )
            ),

            communityChestsRaw = listOf(
                listOf(
                    "Иди на поле ВПЕРЁД",
                    "(Получи ${asCurrency(200, Style.lore)})",
                ),
                listOf(
                    "Банковская ошибка в твою пользу",
                    "Получи ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Визит к врачу",
                    "Заплати ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "На продаже акций ты зарабатываешь ${asCurrency(50, Style.loreTitle)}",
                ),
                listOf(
                    "Выйти из Тюрьмы бесплатно",
                    "Эту карточку можно сохранить",
                    "на будущее или обменять.",
                ),
                listOf(
                    "Ты арестован!",
                    "Отправляйся сразу в тюрьму.",
                    "Ты не проходишь поле ВПЕРЁД",
                    "и не получаешь ${asCurrency(200, Style.lore)}.",
                ),
                listOf(
                    "Отпускные",
                    "Получи ${asCurrency(100, Style.lore)}.",
                ),
                listOf(
                    "Возврат подоходного налога",
                    "Получи ${asCurrency(20, Style.lore)}.",
                ),
                listOf(
                    "День рождения",
                    "Получи в подарок по ${asCurrency(10, Style.lore)} от каждого игрока.",
                ),
                listOf(
                    "Выплата страховки",
                    "Получи ${asCurrency(100, Style.lore)}.",
                ),
                listOf(
                    "Расходы на госпитализацию",
                    "Заплати ${asCurrency(50, Style.lore)}.",
                ),
                listOf(
                    "Оплати обучение в размере ${asCurrency(50, Style.loreTitle)}",
                ),
                listOf(
                    "Получи ${asCurrency(25, Style.loreTitle)} за консультацию",
                ),
                listOf(
                    "Ты должен отремонтировать улицы",
                    "Заплати ${asCurrency(40, Style.lore)} за каждый дом и",
                    "${asCurrency(115, Style.lore)} за каждый отель в твоей собственности.",
                ),
                listOf(
                    "Ты занял второе место на конкурсе красоты!",
                    "Получи ${asCurrency(10, Style.lore)}.",
                ),
                listOf(
                    "Ты получаешь в наследство ${asCurrency(100, Style.loreTitle)}",
                )
            ),
        ),
    )
}


data class Localization(
    val literal: String,

    private val chanceNameRaw: String,
    private val communityChestNameRaw: String,
    private val houseNameRaw: String,
    private val hotelNameRaw: String,

    val bookName: String,
    val bookHoverText: String,
    private val bookItemLoreRaw: List<String>,
    private val bookFirstPageNames: List<Pair<String, Int>>,  // в формате прайс-листа (формат здесь, и просто добавить в book.kt)

    private val propertyNamesRaw: List<String>,

    private val chancesRaw: List<List<String>>,
    private val communityChestsRaw: List<List<String>>,
) {
    val chanceName = "${Style.chance}$chanceNameRaw${CC.RESET}"
    val communityChestName = "${Style.communityChest}$communityChestNameRaw${CC.RESET}"
    val houseName = "${Style.house}$houseNameRaw${CC.RESET}"
    val hotelName = "${Style.hotel}$hotelNameRaw${CC.RESET}"

    val bookAuthor = "KruASe | ${Minenopoly.instance.name}"
    val bookItemLore = bookItemLoreRaw.map { "${Style.lore}$it${CC.RESET}" }
    val bookPriceList = bookFirstPageNames.let {
        listOf(
            "${CC.GREEN}${it[0].first}:${CC.RESET}",
            "${CC.DARK_AQUA}${it[1].first}${CC.RESET}${".".repeat(it[1].second)}${asCurrency(1500)}",
            "${CC.DARK_PURPLE}${
                it[2].first
                    .replace("GO", "${CC.RED}${CC.BOLD}GO${CC.RESET}${CC.DARK_PURPLE}")
                    .replace(" ВПЕРЁД", "${CC.RED}${CC.BOLD} ВПЕРЁД${CC.RESET}${CC.DARK_PURPLE}")
            }${CC.RESET}${".".repeat(it[2].second)}${asCurrency(200)}",
            "${CC.DARK_RED}${it[3].first}${CC.RESET}${".".repeat(it[3].second)}${asCurrency(200, negative = true)}",
            "${CC.GOLD}${it[4].first}${CC.RESET}${".".repeat(it[4].second)}${asCurrency(100, negative = true)}",
            "${CC.DARK_BLUE}${it[5].first}${CC.RESET}${".".repeat(it[5].second)}${asCurrency(50, negative = true)}",
            "${CC.RESET}${".".repeat(it[6].second)}${CC.RED}(${it[6].first})${CC.RESET}"
        ).joinToString("\n")
    }

    val properties: Map<String, Property> = MSD.propertyTypes.zip(
        MSD.propertyTypes.mapIndexed { index, type ->
            val args = type.split(".")
            val logicalType = when {
                args[0] == "street" -> LPT.STREET
                args[0] == "railroad" -> LPT.RAILROAD
                args[1] == "electricity" -> LPT.ELECTRICITY
                args[1] == "water" -> LPT.WATER
                else -> throw IllegalArgumentException()  // should never occur
            }

            Property(
                id = type,
                name = "${
                    when (logicalType) {
                        LPT.STREET -> CC.of(nameToColor[args[1]])
                        LPT.RAILROAD -> CC.GRAY
                        LPT.ELECTRICITY -> CC.GOLD
                        LPT.WATER -> CC.BLUE
                    }
                }${CC.UNDERLINE}${
                    if (logicalType in listOf(LPT.STREET, LPT.RAILROAD))
                        "${args.last()}. "
                    else ""
                }${propertyNamesRaw[index]}${CC.RESET}",
                description = MSD.propertyPrices[index].let {
                    when (literal) {
                        "us" -> when (logicalType) {
                            LPT.STREET -> listOf(
                                "${CC.GREEN}COST${CC.RESET}..............................${
                                    asCurrency(it[0], negative = true)
                                }",
                                "${CC.BLUE}BUILDING COST${CC.RESET}......${asCurrency(it[1], negative = true)}",
                                "${CC.GOLD}BASE RENT${CC.RESET}................${asCurrency(it[2])}",
                                "${CC.LIGHT_PURPLE}COLOR SET${CC.RESET} ${CC.GOLD}RENT${CC.RESET}.." +
                                        asCurrency(it[3]),
                                "1 ${CC.DARK_GREEN}HOUSE${CC.RESET} ${CC.GOLD}RENT${CC.RESET}........" +
                                        asCurrency(it[4]),
                                "2 ${CC.DARK_GREEN}HOUSES${CC.RESET} ${CC.GOLD}RENT${CC.RESET}....." +
                                        asCurrency(it[5]),
                                "3 ${CC.DARK_GREEN}HOUSES${CC.RESET} ${CC.GOLD}RENT${CC.RESET}....." +
                                        asCurrency(it[6]),
                                "4 ${CC.DARK_GREEN}HOUSES${CC.RESET} ${CC.GOLD}RENT${CC.RESET}....." +
                                        asCurrency(it[7]),
                                "${CC.DARK_RED}HOTEL${CC.RESET} ${CC.GOLD}RENT${CC.RESET}............." +
                                        asCurrency(it[8]),
                                "${CC.RED}MORTGAGE${CC.RESET}..................${asCurrency(it[9])}",
                                "${CC.GREEN}UNMORTGAGE${CC.RESET}............${asCurrency(it[10], negative = true)}",
                            )
                            LPT.RAILROAD -> listOf(
                                "${CC.GREEN}COST${CC.RESET}..............................${
                                    asCurrency(it[0], negative = true)
                                }",
                                "",
                                "${CC.GOLD}RENT:${CC.RESET}",
                                "1 ${CC.GRAY}OWNED${CC.RESET}......................${asCurrency(it[1])}",
                                "2 ${CC.GRAY}OWNED${CC.RESET}......................${asCurrency(it[2])}",
                                "3 ${CC.GRAY}OWNED${CC.RESET}......................${asCurrency(it[3])}",
                                "4 ${CC.GRAY}OWNED${CC.RESET}......................${asCurrency(it[4])}",
                                "",
                                "${CC.RED}MORTGAGE${CC.RESET}..................${asCurrency(it[5])}",
                                "${CC.GREEN}UNMORTGAGE${CC.RESET}............${asCurrency(it[6], negative = true)}",
                            )
                            else -> listOf(
                                "${CC.GREEN}COST${CC.RESET}..............................${
                                    asCurrency(it[0], negative = true)
                                }",
                                "",
                                "${CC.GOLD}RENT:${CC.RESET}",
                                "1 ${CC.BLUE}OWNED${CC.RESET}.................${CC.GREEN}DICE×${it[1]}${CC.RESET}",
                                "2 ${CC.BLUE}OWNED${CC.RESET}.................${CC.GREEN}DICE×${it[2]}${CC.RESET}",
                                "",
                                "${CC.RED}MORTGAGE${CC.RESET}..................${asCurrency(it[3])}",
                                "${CC.GREEN}UNMORTGAGE${CC.RESET}............${asCurrency(it[4], negative = true)}",
                            )
                        }

                        "uk" -> when (logicalType) {
                            LPT.STREET -> listOf(
                                "${CC.GREEN}COST${CC.RESET}..............................${
                                    asCurrency(it[0], negative = true)
                                }",
                                "${CC.BLUE}BUILDING COST${CC.RESET}......${asCurrency(it[1], negative = true)}",
                                "${CC.GOLD}BASE RENT${CC.RESET}................${asCurrency(it[2])}",
                                "${CC.LIGHT_PURPLE}COLOR SET${CC.RESET} ${CC.GOLD}RENT${CC.RESET}.." +
                                        asCurrency(it[3]),
                                "1 ${CC.DARK_GREEN}HOUSE${CC.RESET} ${CC.GOLD}RENT${CC.RESET}........" +
                                        asCurrency(it[4]),
                                "2 ${CC.DARK_GREEN}HOUSES${CC.RESET} ${CC.GOLD}RENT${CC.RESET}....." +
                                        asCurrency(it[5]),
                                "3 ${CC.DARK_GREEN}HOUSES${CC.RESET} ${CC.GOLD}RENT${CC.RESET}....." +
                                        asCurrency(it[6]),
                                "4 ${CC.DARK_GREEN}HOUSES${CC.RESET} ${CC.GOLD}RENT${CC.RESET}....." +
                                        asCurrency(it[7]),
                                "${CC.DARK_RED}HOTEL${CC.RESET} ${CC.GOLD}RENT${CC.RESET}............." +
                                        asCurrency(it[8]),
                                "${CC.RED}MORTGAGE${CC.RESET}..................${asCurrency(it[9])}",
                                "${CC.GREEN}UNMORTGAGE${CC.RESET}............${asCurrency(it[10], negative = true)}",
                            )
                            LPT.RAILROAD -> listOf(
                                "${CC.GREEN}COST${CC.RESET}..............................${
                                    asCurrency(it[0], negative = true)
                                }",
                                "",
                                "${CC.GOLD}RENT:${CC.RESET}",
                                "1 ${CC.GRAY}OWNED${CC.RESET}......................${asCurrency(it[1])}",
                                "2 ${CC.GRAY}OWNED${CC.RESET}......................${asCurrency(it[2])}",
                                "3 ${CC.GRAY}OWNED${CC.RESET}......................${asCurrency(it[3])}",
                                "4 ${CC.GRAY}OWNED${CC.RESET}......................${asCurrency(it[4])}",
                                "",
                                "${CC.RED}MORTGAGE${CC.RESET}..................${asCurrency(it[5])}",
                                "${CC.GREEN}UNMORTGAGE${CC.RESET}............${asCurrency(it[6], negative = true)}",
                            )
                            else -> listOf(
                                "${CC.GREEN}COST${CC.RESET}..............................${
                                    asCurrency(it[0], negative = true)
                                }",
                                "",
                                "${CC.GOLD}RENT:${CC.RESET}",
                                "1 ${CC.BLUE}OWNED${CC.RESET}.................${CC.GREEN}DICE×${it[1]}${CC.RESET}",
                                "2 ${CC.BLUE}OWNED${CC.RESET}.................${CC.GREEN}DICE×${it[2]}${CC.RESET}",
                                "",
                                "${CC.RED}MORTGAGE${CC.RESET}..................${asCurrency(it[3])}",
                                "${CC.GREEN}UNMORTGAGE${CC.RESET}............${asCurrency(it[4], negative = true)}",
                            )
                        }

                        "ru" -> when (logicalType) {
                            LPT.STREET -> listOf(
                                "${CC.GREEN}СТОИМОСТЬ${CC.RESET}...............${asCurrency(it[0], negative = true)}",
                                "${CC.BLUE}ЦЕНА ЗДАНИЯ${CC.RESET}.........${asCurrency(it[1], negative = true)}",
                                "${CC.GOLD}БАЗОВАЯ РЕНТА${CC.RESET}....${asCurrency(it[2])}",
                                "${CC.LIGHT_PURPLE}С КОМПЛЕКТОМ${CC.RESET}.......${asCurrency(it[3])}",
                                "С 1` ${CC.DARK_GREEN}ДОМОМ${CC.RESET}...............${asCurrency(it[4])}",
                                "С 2` ${CC.DARK_GREEN}ДОМАМИ${CC.RESET}............${asCurrency(it[5])}",
                                "С 3` ${CC.DARK_GREEN}ДОМАМИ${CC.RESET}............${asCurrency(it[6])}",
                                "С 4` ${CC.DARK_GREEN}ДОМАМИ${CC.RESET}............${asCurrency(it[7])}",
                                "С ${CC.DARK_RED}ОТЕЛЕМ${CC.RESET}...................${asCurrency(it[8])}",
                                "${CC.RED}ЗАЛОЖИТЬ${CC.RESET}.................${asCurrency(it[9])}",
                                "${CC.GREEN}ВЫКУПИТЬ${CC.RESET}.................${asCurrency(it[10], negative = true)}",
                            )
                            LPT.RAILROAD -> listOf(
                                "${CC.GREEN}СТОИМОСТЬ${CC.RESET}...............${asCurrency(it[0], negative = true)}",
                                "",
                                "${CC.GOLD}РЕНТА:${CC.RESET}",
                                "С 1 ${CC.GRAY}ВОКЗАЛОМ${CC.RESET}........${asCurrency(it[1])}",
                                "С 2 ${CC.GRAY}ВОКЗАЛАМИ${CC.RESET}.....${asCurrency(it[2])}",
                                "С 3 ${CC.GRAY}ВОКЗАЛАМИ${CC.RESET}.....${asCurrency(it[3])}",
                                "С 4 ${CC.GRAY}ВОКЗАЛАМИ${CC.RESET}.....${asCurrency(it[4])}",
                                "",
                                "${CC.RED}ЗАЛОЖИТЬ${CC.RESET}.................${asCurrency(it[5])}",
                                "${CC.GREEN}ВЫКУПИТЬ${CC.RESET}.................${asCurrency(it[6], negative = true)}",
                            )
                            else -> listOf(
                                "${CC.GREEN}СТОИМОСТЬ${CC.RESET}...............${asCurrency(it[0], negative = true)}",
                                "",
                                "${CC.GOLD}РЕНТА:${CC.RESET}",
                                "С 1 ${CC.BLUE}СЕРВИСОМ${CC.RESET}.....${CC.GREEN}КУБ×${it[1]}${CC.RESET}",
                                "С 2 ${CC.BLUE}СЕРВИСАМИ${CC.RESET}..${CC.GREEN}КУБ×${it[2]}${CC.RESET}",
                                "",
                                "${CC.RED}ЗАЛОЖИТЬ${CC.RESET}.................${asCurrency(it[3])}",
                                "${CC.GREEN}ВЫКУПИТЬ${CC.RESET}.................${asCurrency(it[4], negative = true)}",
                            )
                        }

                        else -> throw IllegalArgumentException()  // should never occur
                    }
                }
            )
        }
    ).toMap()

    val chances = chancesRaw.map { stringList ->
        stringList.mapIndexed { index, string ->
            if (index == 0) "${Style.loreTitle}$string${CC.RESET}"
            else "${Style.lore}$string${CC.RESET}"
        }
    }

    val communityChests = communityChestsRaw.map { stringList ->
        stringList.mapIndexed { index, string ->
            if (index == 0) "${Style.loreTitle}$string${CC.RESET}"
            else "${Style.lore}$string${CC.RESET}"
        }
    }
}


data class Property(val id: String, val name: String, val description: List<String>)


enum class LogicalPropertyType {
    STREET, RAILROAD, ELECTRICITY, WATER
}


private object Style {
    val loreTitle = "${CC.RESET}${CC.UNDERLINE}"
    val lore = CC.RESET.toString()

    val chance = "${CC.LIGHT_PURPLE}${CC.UNDERLINE}"
    val communityChest = "${CC.AQUA}${CC.UNDERLINE}"
    val house = "${CC.DARK_GREEN}${CC.UNDERLINE}"
    val hotel = "${CC.DARK_RED}${CC.UNDERLINE}"
}
