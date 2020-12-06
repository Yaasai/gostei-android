package com.epicjames.oneshotrpg

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.epicjames.carrinho.CartActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonEntrar.setBackgroundColor(Color.parseColor("#4C3377"))

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        buttonEntrar.setOnClickListener {
            val user = getCurrentUser()
            if (user == null) {
                val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(), 0
                )
            }
            else {
                onOptionsItemSelected()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                Toast.makeText(this, "Já autenticado!", Toast.LENGTH_LONG).show()
            }
            else {
                finishAffinity()
            }
        }
    }

    private fun onOptionsItemSelected(): Boolean {
        startActivity(Intent(this, PerfilActivity::class.java))
        return true
    }

    private fun getCurrentUser(): FirebaseUser? {
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser
    }
}