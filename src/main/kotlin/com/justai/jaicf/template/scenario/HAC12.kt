package com.justai.jaicf.template.scenario


import com.google.gson.Gson
import com.justai.jaicf.activator.regex.regex
import com.justai.jaicf.builder.Scenario
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import java.lang.Exception


val WeatherScenario = Scenario {

    state("GetWeather") {
        activators {
            regex("Погода .+")
        }
        action {
            try {
                val a = activator.regex
                val b = a.toString().split("lastmatch=")
                val c = b[1].subSequence(0, b[1].length - 2).split(" ")[1]
                //val d = c.subSequence(0, c.length - 1)
                val httpClient = HttpClients.createDefault()
                val request = HttpGet(String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=bbd1349fae4a3ded6f4e5ccf2a934b8e&units=metric",c))
                val response = httpClient.execute(request)
                val entity = response.entity
                val result = EntityUtils.toString(entity)
                var map: Map<String, Any> = HashMap()
                map = Gson().fromJson(result, map.javaClass)
                val aaa = map["main"]
                val temp: Map<String, Any> = aaa as Map<String, Any>
                reactions.say("Температура в городе " + c.capitalize() + " " + temp["temp"] + " градусов цельсия, ощущается как " + temp["feels_like"] + " градусов. Давление составляет " + temp["pressure"] + " ГПа." )
            }catch (e: Exception){
                reactions.say("Я не знаю такого города")
            }

        }
    }


    state("weatherHelp"){
        activators {
            regex("Погода")
        }
        action {
            reactions.say("Вы можете узнать прогноз погоды в интересующем Вас городе. Пример запроса: Погода Москва.")
        }
    }
}


