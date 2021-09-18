package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.yandexalice.alice

val HAC8 = Scenario {

    state("helpRestaurant") {
        activators {
            regex("Магазин на борту")
        }
        action {
            reactions.say("Сообщает информацию о вагоне-ресторане. Пример запроса: Хочу заказать еду. Подайте мне яства!")
        }
    }

    state("restaurant") {//Это название состояния, для переходов между ними внутри навыков
        activators {
            regex(".*заказ.*")
            regex(".*Заказ.*")
            regex(".*аго.* .*естора.*")
            regex(".*яства.*")
            regex(".*Яства.*")
            regex(".*ушат.*")
        }

        action {
            reactions.run {
                buttons("Да", "Нет")//кнопки
                alice?.image(//отправка картинки, может не работать
                    "https://i.imgur.com/gIOelEo.jpeg",
                    "В наличии есть салаты, супы.",
                    "Хотите ли Вы пюрешку с котлеткой?"
                            )
            }
        }
    }
}