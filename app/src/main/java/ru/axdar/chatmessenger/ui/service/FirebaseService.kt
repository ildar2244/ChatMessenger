package ru.axdar.chatmessenger.ui.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.axdar.chatmessenger.domain.account.UpdateToken
import ru.axdar.chatmessenger.ui.App
import javax.inject.Inject

class FirebaseService : FirebaseMessagingService() {

    @Inject
    lateinit var updateToken: UpdateToken

    override fun onCreate() {
        super.onCreate()
        App.appComponent.inject(this)
    }

    override fun onMessageReceived(p0: RemoteMessage) {

    }

    override fun onNewToken(token: String) {
        Log.e("fb token", "onNewToken: $token")
        if (token != null) {
            updateToken.invoke(UpdateToken.Params(token))
        }
    }
}