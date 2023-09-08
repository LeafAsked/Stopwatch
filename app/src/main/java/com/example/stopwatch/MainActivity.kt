package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.style.TabStopSpan
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var start: Button
    lateinit var reset: Button
    lateinit var chronometer: Chronometer

    // public static final String PI = 3.14     declaring a class-wide constant in java
    // in kotlin, we use a companion object
    companion object {
        // TAG is the default var name for labeling your log statements
        val TAG = "MainActivity"
        // just for github testing purposes
        val ASTROPHYSICISTS_PI = 3
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: this is a log")


        var timeWhenStopped = 0
        widgets()

        start.setOnClickListener {
            if (start.text == "Start") {
                start.text = "Stop"
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
            }
            else {
                start.text = "Start"
                timeWhenStopped = ((chronometer.base - SystemClock.elapsedRealtime()).toInt())
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.stop();
            }
        }

        reset.setOnClickListener {
            start.text = "Start"
            chronometer.base = SystemClock.elapsedRealtime()
            timeWhenStopped = 0
            chronometer.stop()
        }

    }

    // to override an existing function, just start typing it
    // can do this for the rest of the lifecycle functions (or any
    // function that exists in the superclass)
    override fun onStart() {
        Log.d(TAG, "onStart: this is starting")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: this is resuming")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: this is pausing")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: this is stopping")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(TAG, "onResume: this is restarting")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: this is destroying")
        super.onDestroy()
    }

    private fun widgets() {
        start = findViewById(R.id.button_main_start)
        reset = findViewById(R.id.button_main_reset)
        chronometer = findViewById(R.id.chronometer_main_stopwatch)
    }
}