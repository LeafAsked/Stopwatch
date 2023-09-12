package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.text.style.TabStopSpan
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var start: Button
    lateinit var reset: Button
    lateinit var chronometer: Chronometer
    var working = false
    var timeWhenStopped = 0
    var displayTime = 0L

    // public static final String PI = 3.14     declaring a class-wide constant in java
    // in kotlin, we use a companion object
    companion object {
        // TAG is the default var name for labeling your log statements
        val TAG = "MainActivity"

        // make constants for your key-value pairs
        val STATE_DISPLAY_TIME = "Display Time"
        val STATE_RUNNING = "Running"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: this is a log")

        widgets()
        //restore the saveInstanceState is it exists
        if (savedInstanceState != null) {
            displayTime = savedInstanceState.getLong(STATE_DISPLAY_TIME)
            // solve for base:
            // base = elapsedTime - displayTime
            chronometer.base = SystemClock.elapsedRealtime() - displayTime
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(STATE_RUNNING)) {
                working = savedInstanceState.getBoolean(STATE_RUNNING)
                chronometer.start()
                start.text = "Stop"
            }
        }

        start.setOnClickListener {
            if (start.text == "Start") {
                start.text = "Stop"
                chronometer.base = (SystemClock.elapsedRealtime() + timeWhenStopped)
                working = true
                chronometer.start()
            } else {
                start.text = "Start"
                timeWhenStopped = ((chronometer.base - SystemClock.elapsedRealtime()).toInt())
                chronometer.base = (SystemClock.elapsedRealtime() + timeWhenStopped)
                working = false
                chronometer.stop()
            }
        }
        reset.setOnClickListener {
            start.text = "Start"
            chronometer.base = SystemClock.elapsedRealtime()
            timeWhenStopped = 0
            chronometer.stop()
            working = false
        }
    }

    // use this to preserve state through orientation changes
    override fun onSaveInstanceState(outState: Bundle) {
        // figure out the time that is currently displayed on the screen
        // and save that in a key-value pair in the bundle
        if (working) {
            displayTime = SystemClock.elapsedRealtime() - chronometer.base
        }
        outState.putLong(STATE_DISPLAY_TIME, displayTime)
        super.onSaveInstanceState(outState)

        outState.putBoolean(STATE_RUNNING, working)
        super.onSaveInstanceState(outState)
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