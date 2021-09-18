package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import kotlin.random.Random

val HAC7 = Scenario {
    state("helpInfo") {
        activators {
            regex("Информация о текущей поездке")
        }
        action {
            reactions.say("Сообщает информацию о текущей поездке. Пример запроса: Дай информацию о поездке. Сообщи информацию о поездке")
        }
    }

    state("info") {
        activators {
            regex(".*инф.*")
            regex(".*Инф.*")
        }
        action {
            reactions.say("Номер поезда " + Random.nextInt(151, 700))
            reactions.say("по маршруту ")
            reactions.sayRandom("Москва - Хабаровск.", "Сов.Гавань - Владивосток.", "Москва - Петрозаводск.\n")
            reactions.say(" Следующая станция ")
            reactions.sayRandom("Приамурская.", "Подпорожье.", "Ванино.\n")
            reactions.say(" Время в пути " + Random.nextInt(0, 3) + " ч, ")
            reactions.say(" времени осталось " + Random.nextInt(3, 8) + " ч, ")
            reactions.say(" текущая скорость " + Random.nextInt(40, 120) + " км/ч. ")
        }
    }


}



