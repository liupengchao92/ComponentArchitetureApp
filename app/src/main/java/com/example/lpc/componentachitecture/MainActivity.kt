package com.example.lpc.componentachitecture

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lpc.library.TestUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val string = TestUtils.getString();

        Log.e("MainActivity", "onCreate: ${string}", )



    }
}