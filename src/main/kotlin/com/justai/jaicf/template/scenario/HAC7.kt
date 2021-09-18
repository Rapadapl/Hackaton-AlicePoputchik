package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.yandexalice.activator.alice
import com.justai.jaicf.channel.yandexalice.model.AliceEvent
import com.justai.jaicf.channel.yandexalice.alice
val HAC7 = Scenario {
    state("info") {
        activators {
            intent("INFO")
        }
        action {
            activator.alice?.run {//нужно, если есть активатор
                val slotName = slots["info"]
//если сказанные слова не соответствуют ни одному из навыков
                reactions.go("getinfo")
            }
        }
        state("getinfo") {
            action {
                reactions.say("поезд номер такой-то,маршрут такой-то, остановка такая-то, осталось столько-то времени")
                reactions.alice?.endSession()
            }
        }

        fallback {
            reactions.sayRandom("чего бля“,”мне немного не понятна просьба. Смотрите что я умею:”дай информацию”, “заказать еду из вагона ресторана”")
        }
    }
}
