package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.context.ActionContext
import com.justai.jaicf.context.DefaultActionContext
import com.justai.jaicf.model.scenario.Scenario

import kotlin.random.Random
import java.io.File

val AudiobooksScenario = Scenario{

    state("helpAudiobooks"){
        activators{
            regex("Доступные аудиокниги")
        }
        action {
            reactions.say("Здесь вы можете узнать список доступных аудиокниг. Пример запроса: Покажи список аудиокниг")
        }
    }


    state("audiobooks"){
        activators{
            regex(".*удиокни.*")
            regex(".*удио.*кни.*")
        }
        action{
            val booksNumbers = List(4) {Random.nextInt(from = 0, until = 99)}
            val filename = "D:\\WorkSpace\\aliceskiils\\Hackaton-AlicePoputchik\\src\\main\\resources\\books.txt"
            val file1 = File(filename);
            val lines:List<String> = file1.readLines(charset("UTF-8"))

            reactions.run {
                booksNumbers.forEach{ number ->
                    buttons(lines[number])
                }
            }
        }

    }

}