package com.my71app

import android.content.Context
import android.os.BatteryManager
import android.provider.Settings
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class CustomMethods(private val reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    companion object {
        const val REACT_CLASS = "CustomMethods"
    }

    override fun getName(): String = REACT_CLASS

    @ReactMethod
    fun getBatteryLevel(promise: Promise) {
        try {
            val bm = reactContext.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            promise.resolve(bm?.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY))
        } catch (e: Throwable){
            promise.reject("Create Event Error", e)
        }
    }

    @ReactMethod
    fun getPhoneId(callback: Callback) {
        val phoneId = Settings.Secure.getString(reactContext.contentResolver, Settings.Secure.ANDROID_ID)
        callback.invoke(phoneId)
    }
}