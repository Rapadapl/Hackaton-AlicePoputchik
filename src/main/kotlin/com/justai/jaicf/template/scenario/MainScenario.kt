package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.yandexalice.activator.alice
import com.justai.jaicf.channel.yandexalice.model.AliceEvent
import com.justai.jaicf.channel.yandexalice.alice

val MainScenario = Scenario {

//append(RecordScenario)

    state("main") {
        activators {
            event(AliceEvent.START)
        }

//        action {
//            reactions.run {
//                say("Управдом слушает. Вы хотите сообщить ваши показания счетчиков?")
//                buttons("Да", "Нет")
//                alice?.image(
//                    "https://i.imgur.com/SUSGpqG.jpg",
//                    "Управдом слушает",
//                    "Хотите сообщить ваши показания счетчиков?"
//                )
//            }
//        }

        action {
            reactions.say("ХУЙ БЛЯТЬ")
        }
    }


    state("helmlo"){
        activators {
            intent("TURN.ON")
        }

        action{
            activator.alice?.run{
                val what = slots["what"]
                val where = slots["where"]
                reactions.say(what.toString() + " " + where.toString())
                reactions.alice?.endSession()
            }
        }
    }

    fallback {
        reactions.say("Я ничего не понял")
    }
}