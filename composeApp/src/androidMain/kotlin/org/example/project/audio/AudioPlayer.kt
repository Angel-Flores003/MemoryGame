package org.example.project.audio

import android.media.MediaPlayer
import org.example.project.AppContextHolder
import org.example.project.R

actual class AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    actual fun playSound() {
        val context = AppContextHolder.context
        mediaPlayer = MediaPlayer.create(context, R.raw.answercorrect)
        mediaPlayer?.start()
    }
}