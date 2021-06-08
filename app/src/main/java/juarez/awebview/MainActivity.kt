package juarez.awebview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import juarez.awebview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private var player: SimpleExoPlayer? = null
//    private var playWhenReady = true
//    private var currentWindow = 0
//    private var playbackPosition: Long = 0L
//    private var audioLink = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"
//    private var videoLink =
//        "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoPlayer.setOnClickListener {

            startActivity(Intent(this@MainActivity, PlayerActivity::class.java))
        }
    }

//    private fun initializePlayer() {
//        player = SimpleExoPlayer.Builder(this).build()
//        binding.playerview.player = player
//        val mediaItem = MediaItem.fromUri(videoLink)
//        player?.setMediaItem(mediaItem)
//
//        // add other item
//        val secondMediaItem = MediaItem.fromUri(audioLink)
//        player?.addMediaItem(secondMediaItem)
//
//
//        player?.playWhenReady = playWhenReady
//        player?.seekTo(currentWindow, playbackPosition)
//        player?.prepare()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        if (Build.VERSION.SDK_INT >= 24) {
//            initializePlayer();
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        hideSystemUi();
//        if ((Build.VERSION.SDK_INT < 24 || player == null)) {
//            initializePlayer();
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        if ((Build.VERSION.SDK_INT < 24)) {
//            releasePlayer()
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        if ((Build.VERSION.SDK_INT >= 24)) {
//            releasePlayer()
//        }
//    }
//
//    private fun releasePlayer() {
//        if (player != null) {
//            playWhenReady = player?.playWhenReady!!
//            playbackPosition = player?.currentPosition!!
//            currentWindow = player?.currentWindowIndex!!
//            player?.release();
//            player = null;
//        }
//    }
//
//    @SuppressLint("InlinedApi")
//    private fun hideSystemUi() {
//        binding.playerview.systemUiVisibility = (
//                View.SYSTEM_UI_FLAG_LOW_PROFILE
//                        or View.SYSTEM_UI_FLAG_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
//    }

//    private fun webPlayerExample() {
//        binding.webview.webViewClient = WebViewClient()
//        binding.webview.loadUrl("http://mdstrm.com/embed/606f1bf16897b607cfcfcaaf?autoplay=true")
//        binding.webview.settings.javaScriptEnabled = true
//        binding.webview.settings.setSupportZoom(true)
//    }
}