package com.epicjames.oneshotrpg

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSignUp.setBackgroundColor(Color.parseColor("#4C3377"))
        buttonLogin.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }
}