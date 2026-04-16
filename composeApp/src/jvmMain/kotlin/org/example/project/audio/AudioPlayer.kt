package org.example.project.audio

import javazoom.jl.player.Player

actual class AudioPlayer {
    actual fun playSound() {
        val inputStream = AudioPlayer::class.java.getResourceAsStream("/file.mp3")
        val player = Player(inputStream)
        Thread { player.play() }.start()
    }
}