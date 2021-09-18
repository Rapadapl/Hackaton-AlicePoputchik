package com.justai.jaicf.template.scenario

import com.google.gson.Gson
import com.justai.jaicf.builder.Scenario
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import kotlin.math.roundToInt
import kotlin.random.Random


val HAC9 = Scenario {

    state("helpNews") {
        activators {
            regex("Новости")
        }
        action {
            reactions.say("Вы можете узнать последние новости. Пример запроса: Покажи последние новости.")
        }
    }


    state("getNews") {
        activators {
            regex(".*овост.*")
        }
        action {
            try {
            val httpClient = HttpClients.createDefault()
            val request =
                HttpGet("https://newsapi.org/v2/top-headlines?country=ru&apiKey=ccc63e3a2e0945188ef1df15d2da7572")
            val response = httpClient.execute(request)
            val entity = response.entity
            val result = EntityUtils.toString(entity)
            var map: Map<String, Any> = HashMap()
            map = Gson().fromJson(result, map.javaClass)
            val a: ArrayList<Any> = map["articles"] as ArrayList<Any>
            val maxmax: Double = map["totalResults"] as Double

            val articles = List(4) { Random.nextInt(from = 0, until = 19)}

            reactions.run {
                articles.forEach { number ->
                    run {
                        val b: Map<String, Any> = a[number] as Map<String, Any>
                        buttons(b["title"].toString().subSequence(0,12).toString()+ "...")
                    }
                }
            }

            reactions.say("Список доступных новостей")
            }catch (e: Exception){
               reactions.say("Новости временно недоступны")
            }

        }
    }

}
