package me.kruase.minenopoly

import me.kruase.minenopoly.util.getMoneyItemName


object MinenopolyData {
    val propertyTypes: List<String> = listOf(
        "street.brown.1", "street.brown.2",
        "street.blue.1", "street.blue.2",
        "street.cyan.1", "street.cyan.2", "street.cyan.3",
        "street.pink.1", "street.pink.2", "street.pink.3",
        "street.orange.1", "street.orange.2", "street.orange.3",
        "street.red.1", "street.red.2", "street.red.3",
        "street.yellow.1", "street.yellow.2", "street.yellow.3",
        "street.green.1", "street.green.2", "street.green.3",
        "railroad.1", "railroad.2", "railroad.3", "railroad.4",
        "utility.electric", "utility.water"
        )

    val propertyNames: Map<String, Map<String, String>> = mapOf(
        "us" to propertyTypes.zip(
            listOf(
                "Mediterranean Avenue", "Baltic Avenue",
                "Oriental Avenue", "Vermont Avenue", "Connecticut Avenue",
                "St. Charles Place", "States Avenue", "Virginia Avenue",
                "St. James Place", "Tennessee Avenue", "New York Avenue",
                "Kentucky Avenue", "Indiana Avenue", "Illinois Avenue",
                "Atlantic Avenue", "Ventnor Avenue", "Marvin Gardens",
                "Pacific Avenue", "North Carolina Avenue", "Pennsylvania Avenue",
                "Park Place", "Boardwalk",
                "Reading Railroad", "Pennsylvania Railroad", "B. & O. Railroad", "Short Line",
                "Electric Company", "Water Works"
            )
        ).toMap(),
        "uk" to propertyTypes.zip(
            listOf(
                "Old Kent Road", "Whitechapel Road",
                "The Angel Islington", "Euston Road", "Pentonville Road",
                "Pall Mall", "Whitehall", "Northumberland Avenue",
                "Bow Street", "Marlborough Street", "Vine Street",
                "Strand", "Fleet Street", "Trafalgar Square",
                "Leicester Square", "Coventry Street", "Piccadilly",
                "Regent Street", "Oxford Street", "Bond Street",
                "Park Lane", "Mayfair",
                "King's Cross Station", "Marylebone Station", "Fenchurch Street Station", "Liverpool Street Station",
                "Electric Company", "Water Works"
            )
        ).toMap(),
        "ru" to propertyTypes.zip(
            listOf(
                "Нагатинская улица", "Житная улица",
                "Первая Парковая улица", "Улица Огарёва", "Варшавское шоссе",
                "Улица Полянка", "Улица Сретенка", "Ростовская набережная",
                "Рязанский проспект", "Улица Вавилова", "Рублёвское шоссе",
                "Улица Тверская", "Пушкинская площадь", "Площадь Маяковского",
                "Улица Грузинский Вал", "Новинский бульвар", "Смоленская площадь",
                "Улица Щусева", "Гоголевский бульвар", "Кутузовский проспект",
                "Улица Малая Бронная", "Улица Арбат",
                "Рижская железная дорога", "Курская железная дорога", "Казанская железная дорога", "Ленинградская железная дорога",
                "Электростанция", "Водопровод"
            )
        ).toMap()
    )

    val chances: Map<String, List<List<String>>> = mapOf(
        "us" to listOf(
            listOf(
                "Advance to GO.",
                "(Collect ${getMoneyItemName(200)})"
            ),
            listOf(
                "Advance to Illinois Avenue.",
                "If you pass GO, collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Advance to St. Charles Place.",
                "If you pass GO, collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Advance token to the nearest Utility.",
                "If unowned, you may buy it from the Bank.",
                "If owned, throw dice and pay owner a total",
                "10 (ten) times the amount thrown."
            ),
            listOf(
                "Advance to the nearest Railroad.",
                "If unowned, you may buy it from the Bank.",
                "If owned, pay owner twice the rental",
                "to which they are otherwise entitled."
            ),
            listOf(
                "Bank pays you dividend of ${getMoneyItemName(50)}."
            ),
            listOf(
                "Get out of Jail for free.",
                "This card may be kept until needed,",
                "or traded/sold."
            ),
            listOf(
                "Go back three spaces."
            ),
            listOf(
                "Go directly to Jail.",
                "Do not pass GO, do not collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Make general repairs on all your property:",
                "for each house pay ${getMoneyItemName(25)},",
                "for each hotel pay ${getMoneyItemName(100)}."
            ),
            listOf(
                "Speeding fine ${getMoneyItemName(15)}."
            ),
            listOf(
                "Take a ride to King’s Cross Station.",
                "If you pass GO, collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Take a walk on the board walk.",
                "Advance token to Mayfair."
            ),
            listOf(
                "You have been elected Chairman of the Board.",
                "Pay each player ${getMoneyItemName(50)}."
            ),
            listOf(
                "Your building loan matures.",
                "Receive ${getMoneyItemName(150)}."
            ),
            listOf(
                "You have won a crossword competition.",
                "Collect ${getMoneyItemName(100)}."
            )
        ),
        "uk" to listOf(
            listOf(
                "Advance to GO.",
                "(Collect ${getMoneyItemName(200)})"
            ),
            listOf(
                "Advance to Trafalgar Square.",
                "If you pass GO, collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Advance to Pall Mall.",
                "If you pass GO, collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Advance token to the nearest Utility.",
                "If unowned, you may buy it from the Bank.",
                "If owned, throw dice and pay owner a total",
                "10 (ten) times the amount thrown."
            ),
            listOf(
                "Advance to the nearest Railroad.",
                "If unowned, you may buy it from the Bank.",
                "If owned, pay owner twice the rental",
                "to which they are otherwise entitled."
            ),
            listOf(
                "Bank pays you dividend of ${getMoneyItemName(50)}."
            ),
            listOf(
                "Get out of Jail for free.",
                "This card may be kept until needed,",
                "or traded/sold."
            ),
            listOf(
                "Go back three spaces."
            ),
            listOf(
                "Go directly to Jail.",
                "Do not pass GO, do not collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Make general repairs on all your property:",
                "for each house pay ${getMoneyItemName(25)},",
                "for each hotel pay ${getMoneyItemName(100)}."
            ),
            listOf(
                "Speeding fine ${getMoneyItemName(15)}."
            ),
            listOf(
                "Take a trip to Reading Railroad.",
                "If you pass GO, collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Take a walk on the Boardwalk.",
                "Advance token to Boardwalk."
            ),
            listOf(
                "You have been elected Chairman of the Board.",
                "Pay each player ${getMoneyItemName(50)}."
            ),
            listOf(
                "Your building loan matures.",
                "Receive ${getMoneyItemName(150)}."
            ),
            listOf(
                "You have won a crossword competition.",
                "Collect ${getMoneyItemName(100)}."
            )
        ),
        "ru" to listOf(
            listOf(
                "Иди на поле ВПЕРЁД.",
                "(Получи ${getMoneyItemName(200)})"
            ),
            listOf(
                "Отправляйся на Площадь Маяковского.",
                "Если проходишь поле ВПЕРЁД, получи ${getMoneyItemName(200)}."
            ),
            listOf(
                "Отправляйся на улицу Полянка.",
                "Если проходишь поле ВПЕРЁД, получи ${getMoneyItemName(200)}."
            ),
            listOf(
                "Отправляйся на ближайшее Коммунальное Предприятие.",
                "Если оно никому не принадлежит, можешь заплатить в Банк и купить его.",
                "Если оно является собственностью другого игрока, бросай кубики и",
                "заплати владельцу ренту, равную сумме выпавших очков, умноженных на 10."
            ),
            listOf(
                "Отправляйся на ближайшую Станцию.",
                "Если она никому не принадлежит, можешь заплатить в Банк и купить её.",
                "Если она является собственностью другого игрока, заплати владельцу",
                "ренту в два раза больше обычной."
            ),
            listOf(
                "Банк выплачивает тебе дивиденды",
                "в размере ${getMoneyItemName(50)}."
            ),
            listOf(
                "Выйти из Тюрьмы бесплатно.",
                "Эту карточку можно сохранить",
                "на будущее или обменять."
            ),
            listOf(
                "Вернись на три поля назад."
            ),
            listOf(
                "Ты арестован!",
                "Отправляйся сразу в тюрьму.",
                "Ты не проходишь поле ВПЕРЁД",
                "и не получаешь ${getMoneyItemName(200)}."
            ),
            listOf(
                "Капитальный ремонт всей твоей собственности:",
                "заплати ${getMoneyItemName(25)} за каждый дом и ${getMoneyItemName(100)} за каждый отель."
            ),
            listOf(
                "Штраф за превышение скорости ${getMoneyItemName(15)}."
            ),
            listOf(
                "Прокатись по Рижской железной дороге.",
                "Если проходишь поле ВПЕРЁД, получи ${getMoneyItemName(200)}."
            ),
            listOf(
                "Отправляйся на улицу Арбат.",
            ),
            listOf(
                "Тебя избрали председателем совета директоров.",
                "Заплати каждому игроку по ${getMoneyItemName(50)}."
            ),
            listOf(
                "Ссуда на строительство.",
                "Получи ${getMoneyItemName(150)}."
            ),
            listOf(
                "Ты выиграл чемпионат по кроссвордам!",
                "Получи ${getMoneyItemName(100)}."
            )
        )
    )
    val communityChests: Map<String, List<List<String>>> = mapOf(
        "us" to listOf(
            listOf(
                "Advance to GO.",
                "(Collect ${getMoneyItemName(200)})"
            ),
            listOf(
                "Bank error in your favor.",
                "Collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Doctor's fees.",
                "Pay ${getMoneyItemName(50)}."
            ),
            listOf(
                "From sale of stock you get ${getMoneyItemName(50)}."
            ),
            listOf(
                "Get out of Jail for free.",
                "This card may be kept until needed,",
                "or traded/sold."
            ),
            listOf(
                "Go directly to Jail.",
                "Do not pass GO, do not collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Holiday fund matures.",
                "Receive ${getMoneyItemName(100)}."
            ),
            listOf(
                "Income tax refund.",
                "Collect ${getMoneyItemName(20)}."
            ),
            listOf(
                "It is your birthday.",
                "Collect ${getMoneyItemName(10)} from every player."
            ),
            listOf(
                "Life insurance matures.",
                "Collect ${getMoneyItemName(100)}."
            ),
            listOf(
                "Hospital fees.",
                "Pay ${getMoneyItemName(50)}."
            ),
            listOf(
                "School fees.",
                "Pay ${getMoneyItemName(50)}."
            ),
            listOf(
                "Receive ${getMoneyItemName(25)} consultancy fee."
            ),
            listOf(
                "You are assessed for street repairs:",
                "pay ${getMoneyItemName(40)} per house and ${getMoneyItemName(115)} per hotel you own."
            ),
            listOf(
                "You have won second prize in a beauty contest.",
                "Collect ${getMoneyItemName(10)}."
            ),
            listOf(
                "You inherit ${getMoneyItemName(100)}."
            )
        ),
        "uk" to listOf(
            listOf(
                "Advance to GO.",
                "(Collect ${getMoneyItemName(200)})"
            ),
            listOf(
                "Bank error in your favor.",
                "Collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Doctor's fees.",
                "Pay ${getMoneyItemName(50)}."
            ),
            listOf(
                "From sale of stock you get ${getMoneyItemName(50)}."
            ),
            listOf(
                "Get out of Jail for free.",
                "This card may be kept until needed,",
                "or traded/sold."
            ),
            listOf(
                "Go directly to Jail.",
                "Do not pass GO, do not collect ${getMoneyItemName(200)}."
            ),
            listOf(
                "Holiday fund matures.",
                "Receive ${getMoneyItemName(100)}."
            ),
            listOf(
                "Income tax refund.",
                "Collect ${getMoneyItemName(20)}."
            ),
            listOf(
                "It is your birthday.",
                "Collect ${getMoneyItemName(10)} from every player."
            ),
            listOf(
                "Life insurance matures.",
                "Collect ${getMoneyItemName(100)}."
            ),
            listOf(
                "Hospital fees.",
                "Pay ${getMoneyItemName(50)}."
            ),
            listOf(
                "School fees.",
                "Pay ${getMoneyItemName(50)}."
            ),
            listOf(
                "Receive ${getMoneyItemName(25)} consultancy fee."
            ),
            listOf(
                "You are assessed for street repairs:",
                "pay ${getMoneyItemName(40)} per house and ${getMoneyItemName(115)} per hotel you own."
            ),
            listOf(
                "You have won second prize in a beauty contest.",
                "Collect ${getMoneyItemName(10)}."
            ),
            listOf(
                "You inherit ${getMoneyItemName(100)}."
            )
        ),
        "ru" to listOf(
            listOf(
                "Иди на поле ВПЕРЁД.",
                "(Получи ${getMoneyItemName(200)})"
            ),
            listOf(
                "Банковская ошибка в твою пользу.",
                "Получи ${getMoneyItemName(200)}."
            ),
            listOf(
                "Визит к врачу.",
                "Заплати ${getMoneyItemName(50)}."
            ),
            listOf(
                "На продаже акций ты зарабатываешь ${getMoneyItemName(50)}."
            ),
            listOf(
                "Выйти из Тюрьмы бесплатно.",
                "Эту карточку можно сохранить",
                "на будущее или обменять."
            ),
            listOf(
                "Ты арестован!",
                "Отправляйся сразу в тюрьму.",
                "Ты не проходишь поле ВПЕРЁД",
                "и не получаешь ${getMoneyItemName(200)}."
            ),
            listOf(
                "Отпускные.",
                "Получи ${getMoneyItemName(100)}."
            ),
            listOf(
                "Возврат подоходного налога.",
                "Получи ${getMoneyItemName(20)}."
            ),
            listOf(
                "День рождения.",
                "Получи в подарок по ${getMoneyItemName(10)} от каждого игрока."
            ),
            listOf(
                "Выплата страховки.",
                "Получи ${getMoneyItemName(100)}."
            ),
            listOf(
                "Расходы на госпитализацию.",
                "Заплати ${getMoneyItemName(50)}."
            ),
            listOf(
                "Оплати обучение в размере ${getMoneyItemName(50)}."
            ),
            listOf(
                "Получи ${getMoneyItemName(25)} за консультацию."
            ),
            listOf(
                "Ты должен отремонтировать улицы:",
                "заплати ${getMoneyItemName(40)} за каждый дом и ${getMoneyItemName(115)} за",
                "каждый отель в твоей собственности."
            ),
            listOf(
                "Ты занял второе место на конкурсе красоты!",
                "Получи ${getMoneyItemName(10)}."
            ),
            listOf(
                "Ты получаешь в наследство ${getMoneyItemName(100)}."
            )
        )
    )
}
