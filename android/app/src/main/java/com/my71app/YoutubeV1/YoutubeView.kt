package com.my71app.YoutubeV1

import android.view.ViewGroup
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubeView(
    private val callerContext: ReactApplicationContext
) : SimpleViewManager<YouTubePlayerView>() {

    private lateinit var playerView: YouTubePlayerView

    override fun getName() = "RNUtubeView"

    override fun createViewInstance(context: ThemedReactContext): YouTubePlayerView {
        playerView = YouTubePlayerView(context)
        playerView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        playerView.enableAutomaticInitialization = false

        return playerView
    }

    @ReactProp(name = "id")
    fun playVideo(view: YouTubePlayerView, id: String) {
        if (id == null) return;

        val iFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(0)
            .build()

        val youTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(id, 0f)
                youTubePlayer.pause()
            }
        }

        view.initialize(youTubePlayerListener, iFramePlayerOptions)
    }

}

