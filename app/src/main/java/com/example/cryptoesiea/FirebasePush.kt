package com.example.cryptoesiea

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class FirebasePush: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("TAG", "The token refreshed: $token")
    }

}