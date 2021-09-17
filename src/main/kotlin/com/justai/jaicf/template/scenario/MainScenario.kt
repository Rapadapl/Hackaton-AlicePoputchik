package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.dialogflow.DialogflowIntent
import com.justai.jaicf.activator.dialogflow.dialogflow
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
        action {
            reactions.say("ХУЙ БЛЯТЬ")
        }
    }


    state("helmlo"){
        activators {
            intent("Hto ya")
        }

        action(dialogflow){
                val what = activator.slots["HtoYa"]
            if (what != null) {
                reactions.say("Ты"+ String(what.toByteArray(), charset("UTF-8")))
            }
        }
    }

    state("alice"){
        activators {
            intent("TURN.ON")
        }
        action{
            activator.alice?.run {

                val what = slots["what"]
                    reactions.say("Ты"+ what.toString())
            }
            }
        }


    fallback {
        reactions.say("Я ничего не понял")
    }
}