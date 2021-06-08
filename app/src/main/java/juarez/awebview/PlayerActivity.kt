package juarez.awebview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.MimeTypes
import juarez.awebview.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private val TAG = "PlayerActivity"
    private lateinit var binding: ActivityPlayerBinding
    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0L
    private var audioLink = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"
    private var videoLink =
        "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
    private lateinit var playbackStateListener: PlaybackStateListener

    inner class PlaybackStateListener : Player.EventListener {

        override fun onPlaybackStateChanged(state: Int) {
            super.onPlaybackStateChanged(state)
            var playbackState = ""
            playbackState = when (state) {
                Player.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
                Player.STATE_BUFFERING -> {
                    binding.playerview.hideController()
                    binding.progressPlayerView.visibility = View.VISIBLE
                    "ExoPlayer.STATE_BUFFERING -"
                }
                Player.STATE_READY -> {
                    binding.playerview.showController()
                    binding.progressPlayerView.visibility = View.GONE
                    "ExoPlayer.STATE_READY     -"
                }
                Player.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
                else -> "UNKNOWN_STATE             -"
            }
            Log.d(TAG, playbackState)
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            if(isPlaying){
                Log.d(TAG, "PLAY")
            }else {
                Log.d(TAG, "PAUSE")
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playbackStateListener = PlaybackStateListener()

        binding.btnCloseExo.setOnClickListener {
            finish()
        }
    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        binding.playerview.player = player


        val media = MediaItem.fromUri(videoLink)

        player?.setMediaItem(media)

        player?.addMediaItem(media)

        player?.playWhenReady = playWhenReady
        player?.seekTo(currentWindow, playbackPosition)
        player?.addListener(playbackStateListener)
        player?.prepare()
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((Build.VERSION.SDK_INT < 24 || player == null)) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if ((Build.VERSION.SDK_INT < 24)) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if ((Build.VERSION.SDK_INT >= 24)) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        if (player != null) {
            playWhenReady = player?.playWhenReady!!
            playbackPosition = player?.currentPosition!!
            currentWindow = player?.currentWindowIndex!!
            player?.removeListener(playbackStateListener)
            player?.release();
            player = null;
        }
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        binding.playerview.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}