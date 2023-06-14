package com.my71app.YoutubeV2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.my71app.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class YoutubeFragment : Fragment(R.layout.fragment_youtube) {

    private lateinit var youTubePlayerView: YouTubePlayerView
    private var videoUrl: String? = null
    private lateinit var youTubePlayer: YouTubePlayer


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        youTubePlayerView = view.findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.enableAutomaticInitialization = false
        initializePlayer()
    }


    fun onSetVideoId(id: String) {
        videoUrl = id
    }

    fun onPausePlaying(){
        youTubePlayer.pause()
    }

    fun onResumePlaying(){
        youTubePlayer.play()
    }


    private fun initializePlayer() {
        val iFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(1)
            .build()

        val youTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@YoutubeFragment.youTubePlayer = youTubePlayer
                videoUrl?.let { youTubePlayer.loadVideo(it, 0f) }
                youTubePlayer.pause()
            }
        }

        youTubePlayerView.initialize(youTubePlayerListener, iFramePlayerOptions)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(youTubePlayerView != null){
            youTubePlayerView.release()
            youTubePlayer.pause()
        }
    }


}