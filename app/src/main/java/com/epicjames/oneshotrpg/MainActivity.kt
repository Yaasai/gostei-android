package com.epicjames.oneshotrpg

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}

        setContentView(R.layout.activity_main)

        buttonRegistrar.setBackgroundColor(Color.parseColor("#4C3377"))
        buttonEntrar.setBackgroundColor(Color.parseColor("#FFFFFF"))

        buttonRegistrar.setOnClickListener {
            val i = Intent(this, RegistrarActivity::class.java)
            startActivity(i)
        }

        buttonEntrar.setOnClickListener {
            val i = Intent(this, EntrarActivity::class.java)
            startActivity(i)
        }
    }
}