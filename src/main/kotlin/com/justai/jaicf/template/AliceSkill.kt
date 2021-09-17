package com.justai.jaicf.template

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.catchall.CatchAllActivator
import com.justai.jaicf.activator.dialogflow.DialogflowAgentConfig
import com.justai.jaicf.activator.dialogflow.DialogflowConnector
import com.justai.jaicf.activator.dialogflow.DialogflowIntentActivator
import com.justai.jaicf.activator.dialogflow.dialogflow
import com.justai.jaicf.activator.event.BaseEventActivator
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.channel.yandexalice.activator.AliceIntentActivator
import com.justai.jaicf.context.manager.InMemoryBotContextManager
import com.justai.jaicf.context.manager.mongo.MongoBotContextManager
import com.justai.jaicf.template.scenario.MainScenario
import com.mongodb.client.MongoClients

val dialogflowActivator = DialogflowIntentActivator.Factory(
    DialogflowConnector(
        DialogflowAgentConfig(
            language = "ru",
            credentialsResourcePath = "/df3.json"
                             )
                       )
                                                           )

val skill = BotEngine(
    scenario = MainScenario,
    activators = arrayOf(
        AliceIntentActivator,
       // dialogflowActivator,
        RegexActivator,
        BaseEventActivator,
        CatchAllActivator
    )
)