package com.demoexample.mystreamchat

import android.app.Application
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.livedata.ChatDomain

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        val chatClient=
            ChatClient.Builder(getString(R.string.api_key),this)
                .logLevel(ChatLogLevel.ALL).build()

        ChatDomain.Builder(chatClient,this)
    }
}