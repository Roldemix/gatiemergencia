package com.example.gatiemergencias.sound

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool

object SoundManager {
    private var soundPool: SoundPool? = null
    private val soundMap = mutableMapOf<Int, Int>()
    private var maxStreams = 4
    private var loaded = false

    fun init(context: Context, maxStreams: Int = 4) {
        if (soundPool != null) return
        this.maxStreams = maxStreams
        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(attrs)
            .setMaxStreams(maxStreams)
            .build()
        soundPool?.setOnLoadCompleteListener { _, _, _ -> loaded = true }
    }

    fun loadFromRes(context: Context, resId: Int): Int {
        init(context)
        val id = soundPool?.load(context, resId, 1) ?: 0
        soundMap[resId] = id
        return id
    }

    fun loadFromAsset(context: Context, assetPath: String): Int {
        init(context)
        val afd: AssetFileDescriptor = context.assets.openFd(assetPath)
        val id = soundPool?.load(afd, 1) ?: 0
        soundMap[assetPath.hashCode()] = id
        return id
    }

    fun play(resId: Int, loop: Int = 0, volume: Float = 1.0f, rate: Float = 1.0f) {
        val soundId = soundMap[resId] ?: return
        soundPool?.play(soundId, volume, volume, 1, loop, rate)
    }

    // Convenience: play longer audio with MediaPlayer (one-shot)
    fun playWithMediaPlayer(context: Context, resId: Int, onCompletion: (() -> Unit)? = null) {
        try {
            val mp = MediaPlayer.create(context, resId)
            mp?.setOnCompletionListener {
                onCompletion?.invoke()
                it.release()
            }
            mp?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun release() {
        soundPool?.release()
        soundPool = null
        soundMap.clear()
        loaded = false
    }
}
