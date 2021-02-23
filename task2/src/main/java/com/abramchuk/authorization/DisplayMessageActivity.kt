package com.abramchuk.authorization

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        findViewById<TextView>(R.id.textView5).apply {
            text = "Hello, $message"
        }
        Log.i("DisplayMessageActivity", "onCreate Called")
    }

    override fun onStart() {
        super.onStart()
        Log.i("DisplayMessageActivity", "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("DisplayMessageActivity", "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("DisplayMessageActivity", "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("DisplayMessageActivity", "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("DisplayMessageActivity", "onDestroy Called")
    }
}