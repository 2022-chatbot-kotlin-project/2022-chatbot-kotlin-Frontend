package com.hygge.hygge.chatbot

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {

            //Hello
            message.contains("안녕") -> {
//                when (random) {
//                    0 -> "Hello there!"
//                    1 -> "Sup"
//                    2 -> "Buongiorno!"
//                    else -> "error" }
                "안녕하세요:)"
            }

            //How are you?
            message.contains("도움")-> {
                "000-0000에 전화하면 24시간 상담이 가능합니다. 힘든 고민을 이야기 해보세요"
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}