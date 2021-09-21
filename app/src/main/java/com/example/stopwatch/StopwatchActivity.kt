package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_stopwtch.*
import java.util.*

class StopwatchActivity : AppCompatActivity() {

    private var seconds: Int = 0
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running"); }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwtch)
        runTimer()
    }

    private fun runTimer() {
        val handler = Handler()
        handler.post(
            object: Runnable {
                override fun run() {
                    val hours = seconds / 3600
                    val minutes = (seconds % 3600) / 60
                    val secs = seconds % 60
                    val time = String.format(
                        Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs
                    )
                    time_view.text = time
                    if (running) {
                        seconds++
                    }
                    handler.postDelayed(this,1000)
                }
            })
    }

    fun onClickStart(view: View) {
        running = true
    }

    fun onClickStop(view: View) {
        running = false
    }

    fun onClickReset(view: View) {
        running = false
        seconds = 0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds",seconds)
        outState.putBoolean("running", running)
    }

}