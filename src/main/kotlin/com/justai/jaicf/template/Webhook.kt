package com.justai.jaicf.template

import com.justai.jaicf.channel.http.httpBotRouting
import com.justai.jaicf.channel.yandexalice.AliceChannel
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, System.getenv("PORT")?.toInt() ?: 8888) {
        routing {
            httpBotRouting("/" to AliceChannel(
                skill,
                System.getenv("OAUTH_TOKEN") ?: "AQAAAAAyWM3GAAT7owuw_tRUhkKjkYpi-DVBoiE"))
        }
    }.start(wait = true)
}