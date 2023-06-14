package com.my71app.CustomViewV1

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class CustomView : SimpleViewManager<View>() {

    companion object {
        const val REACT_CLASS = "CustomView"
        const val PROP_TEXT = "text"
        const val PROP_COLOR = "color"
        const val DEFAULT_TEXT = "Texto desde Android"
    }

    override fun getName(): String {
        return REACT_CLASS
    }


    override fun createViewInstance(context: ThemedReactContext): View {
        val textView = TextView(context)
        textView.text = DEFAULT_TEXT
        textView.gravity = Gravity.CENTER

        return textView
    }

    @ReactProp(name = PROP_TEXT)
    fun setText(view: View, text: String?) {
        if (view is TextView) {
            view.text = text ?: DEFAULT_TEXT
        }
    }

    @ReactProp(name = PROP_COLOR)
    fun setColor(view: View, color: String?) {
        if (view is TextView) {
            view.setTextColor(Color.parseColor(color ?: "#000000"))
        }
    }

    override fun onDropViewInstance(view: View) {
        if (view is TextView) {
            view.text = null
        }
        super.onDropViewInstance(view)
    }
}