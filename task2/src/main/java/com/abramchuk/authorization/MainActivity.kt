package com.abramchuk.authorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val user = "Admin"
    private val password = "admin@Huawei"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity", "onCreate Called")
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy Called")
    }

    fun authorize(view: View){
        val inputLogin = findViewById<EditText>(R.id.editTextTextPersonName)
        val inputPass = findViewById<EditText>(R.id.editTextTextPassword2)
        val login = inputLogin.text.toString()
        val pass = inputPass.text.toString()
        if(login==user && pass==password){
            val intent = Intent(this, DisplayMessageActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, login)
            }
            startActivity(intent)
        }
        else{
            findViewById<TextView>(R.id.wrong_input_textview).visibility = View.VISIBLE
        }
    }
}