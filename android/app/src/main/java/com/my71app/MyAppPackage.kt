package com.my71app // replace your-app-name with your app’s name

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.BaseViewManager
import com.facebook.react.uimanager.LayoutShadowNode
import com.my71app.CustomViewV1.CustomView
import com.my71app.YoutubeV2.MyViewManager

class MyAppPackage : ReactPackage {

    override fun createViewManagers(
        reactContext: ReactApplicationContext
    ): MutableList<BaseViewManager<out View, LayoutShadowNode>> =
        mutableListOf(MyViewManager(reactContext), CustomView())

    override fun createNativeModules(
        reactContext: ReactApplicationContext
    ): MutableList<NativeModule> = mutableListOf(CustomMethods(reactContext))
}