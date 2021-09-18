package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.regex.regex
import com.justai.jaicf.builder.Scenario
import java.util.*
import kotlin.random.Random

val BuyTicket = Scenario {
    state("helpBuyTicket") {
        activators {
            regex("Покупка билетов")
        }
        action {
            reactions.say("Вы можете купить билет на поезд. Пример запроса: Купить билет из Хабаровска в Москву.")
        }
    }

    state("buyTicket") {
        activators {
            regex(".* из .+ в .+")
        }
        action {
            val a = activator.regex
            val b = a.toString().split("lastmatch=")
            val c = b[1].subSequence(0, b[1].length - 2)
            val firstCity = c.split(" из ")[1].split(" в ")[0]
            val secondCity = c.split(" в ")[1]
            val number: Int = Random.nextInt(0, 5)
            if (number == 0) {
                reactions.say("По вашему запросу билетов не найдено.")
            } else {
                reactions.say("Найдено следующее количество билетов: $number")

                val queue: ArrayList<Int> = ArrayList<Int>()

                for (i in 0 until number) {
                    queue.add(Random.nextInt(0,23))
                }
                queue.sort()
                reactions.say(" .Вот список доступных билетов из ${firstCity.capitalize()} в ${secondCity.capitalize()}: ")
                reactions.run{
                    queue.forEach { i ->
                        buttons("В $i:00 на поезде №"+ Random.nextInt(151,700))
                    }
                }

            }
        }
    }
}