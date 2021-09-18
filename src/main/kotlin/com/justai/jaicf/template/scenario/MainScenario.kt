package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.yandexalice.model.AliceEvent

val MainScenario = Scenario {

    append(WeatherScenario)
    append(HAC7)
    append(HAC8)
    append(AudiobooksScenario)
    append(HAC9)
    append(BuyTicket)

    state("main") {
        activators {
            event(AliceEvent.START)
        }
        action {
            reactions.say("Привет. Вас приветствует голосовой помощник приложения попутчик. Чтобы узнать подробную информацию попросите помощи")
        }
    }

    state("help") {

        activators {
            regex(".*помог.*")
            regex(".*помощ.*")
            regex(".*Помог.*")
            regex(".*помощ.*")
        }
        action {

            reactions.run {
                say("Набор функционала представлен в виде кнопок. Чтобы подробнее узнать, как пользоваться функциями, нажмите на соответсвующие кнопки")
                buttons(
                    "Погода",
                    "Покупка билетов",
                    "Доступные аудиокниги",
                    "Новости",
                    "Магазин на борту",
                    "Информация о текущей поездке"
                       )
            }
        }
    }

    fallback {
        reactions.say("Чтобы получить справку, просто скажите 'Помощь'")
    }
}