package com.epicjames.oneshotrpg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EntrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrar)

        //actionbar
        val actionbar = supportActionBar
        //set back button
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}