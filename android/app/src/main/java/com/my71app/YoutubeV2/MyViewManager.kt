package com.my71app.YoutubeV2

import android.view.Choreographer
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp


class MyViewManager(
    private val reactContext: ReactApplicationContext
) : ViewGroupManager<FrameLayout>() {

    private var viewIds = mutableMapOf<Int, String>()
    lateinit var myFragment: YoutubeFragment

    override fun getName() = REACT_CLASS

    /**
     * Return a FrameLayout which will later hold the Fragment
     */
    override fun createViewInstance(reactContext: ThemedReactContext) =
        FrameLayout(reactContext).apply {
            tag = VIEW_MANAGER_TAG
        }

    /**
     * Map the "create" command to an integer
     */
    override fun getCommandsMap() = mapOf("create" to COMMAND_CREATE, "pause" to COMMAND_PAUSE, "resume" to COMMAND_RESUME)

    /**
     * Handle "create" command (called from JS) and call createFragment method
     */
    override fun receiveCommand(
        root: FrameLayout,
        commandId: String,
        args: ReadableArray?
    ) {
        super.receiveCommand(root, commandId, args)
        val reactNativeViewId = requireNotNull(args).getInt(0)
        when (commandId.toInt()) {
            COMMAND_CREATE -> createFragment(root, reactNativeViewId)
            COMMAND_PAUSE -> pausePlaying(root, reactNativeViewId);
            COMMAND_RESUME -> resumePlaying(root, reactNativeViewId);
        }
    }


    @ReactProp(name = "videoId")
    fun setVideoId(view: FrameLayout, id: String) {
        val viewId = view.id
        viewIds[viewId] = id
    }

    private fun pausePlaying(root: FrameLayout, reactNativeViewId: Int){
        val activity = reactContext.currentActivity as FragmentActivity?
        val youtubeFragment = activity?.supportFragmentManager?.findFragmentById(reactNativeViewId) as YoutubeFragment
        if(youtubeFragment != null) {
            youtubeFragment.onPausePlaying();
        }
    }


    private fun resumePlaying(root: FrameLayout, reactNativeViewId: Int){
        val activity = reactContext.currentActivity as FragmentActivity?
        val youtubeFragment = activity?.supportFragmentManager?.findFragmentById(reactNativeViewId) as YoutubeFragment
        if(youtubeFragment != null) {
            youtubeFragment.onResumePlaying();
        }
    }

    /**
     * Replace your React Native view with a custom fragment
     */
    private fun createFragment(root: FrameLayout, reactNativeViewId: Int) {
        val parentView = root.findViewById<ViewGroup>(reactNativeViewId)
        setupLayout(parentView)

        myFragment = YoutubeFragment()

        myFragment.onSetVideoId(requireNotNull(viewIds[reactNativeViewId]))

        val fragmentTag = "YoutubeFragment_$reactNativeViewId"

        val activity = reactContext.currentActivity as FragmentActivity
        activity.supportFragmentManager
            .beginTransaction()
            .replace(reactNativeViewId, myFragment, fragmentTag)
            .commit()
    }

    private fun setupLayout(view: View) {
        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                manuallyLayoutChildren(view as ViewGroup)
                view.viewTreeObserver.dispatchOnGlobalLayout()
                Choreographer.getInstance().postFrameCallback(this)
            }
        })
    }

    /**
     * Layout all children properly
     */
    open fun manuallyLayoutChildren(view: ViewGroup) {
        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)
            child.measure(
                View.MeasureSpec.makeMeasureSpec(
                    view.measuredWidth,
                    View.MeasureSpec.EXACTLY
                ),
                View.MeasureSpec.makeMeasureSpec(
                    view.measuredHeight,
                    View.MeasureSpec.EXACTLY
                )
            )
            child.layout(0, 0, child.measuredWidth, child.measuredHeight)
        }
    }

    override fun onDropViewInstance(view: FrameLayout) {
        super.onDropViewInstance(view)
        if(myFragment != null){
            myFragment.onDestroy()
        }
    }




    companion object {
        private const val REACT_CLASS = "MyViewManager"
        private const val COMMAND_CREATE = 1
        private const val COMMAND_PAUSE = 2
        private const val COMMAND_RESUME = 3
        private const val VIEW_MANAGER_TAG = "MyViewManagerTag"
    }
}