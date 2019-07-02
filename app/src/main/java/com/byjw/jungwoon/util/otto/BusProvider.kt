package com.byjw.jungwoon.util.otto

import android.os.Handler
import android.os.Looper
import com.squareup.otto.Bus

object BusProvider : Bus() {
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun post(event: Any) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event)
        } else {
            mainHandler.post { super@BusProvider.post(event) }
        }
    }
}