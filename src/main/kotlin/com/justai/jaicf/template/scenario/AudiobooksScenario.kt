package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.context.ActionContext
import com.justai.jaicf.context.DefaultActionContext
import com.justai.jaicf.model.scenario.Scenario

import kotlin.random.Random
import java.io.File

val AudiobooksScenario = Scenario{

    state("audiobooks"){
        activators{
            //event(AliceEvent.)
            //Покажи список книг
            //Или покажи список аудиокниг
        }
        action{
            val booksNumbers = List(4) {Random.nextInt(from = 0, until = 99)}
            val filename = "Hackaton-AlicePoputchik/src/main/kotlin/com/justai/jaicf/template/scenario/books.txt"
            val file1 = File(filename);
            val lines:List<String> = file1.readLines(charset("UTF-8"))

            reactions.run {
                booksNumbers.forEach{ number ->
                    buttons(lines[number])
                }

//                say("Управдом слушает. Вы хотите сообщить ваши показания счетчиков?")
//                buttons("Да", "Нет")
//                alice?.image(
//                    "https://i.imgur.com/SUSGpqG.jpg",
//                    "Управдом слушает",
//                    "Хотите сообщить ваши показания счетчиков?"
//                )
            }
        }

    }

}