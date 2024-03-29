package com.epicjames.oneshotrpg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_perfil.*

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val user = FirebaseAuth.getInstance().currentUser

        textNome.setText(getString(R.string.bemvindo) + user?.displayName + "!")

        buttonCompras.setOnClickListener {
            val intent = Intent(this, ComprasActivity::class.java)
            startActivity(intent)
        }

        buttonSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonProdutos.setOnClickListener {
            val intent = Intent(this, ProdutosActivity::class.java)
            startActivity(intent)
        }

        buttonCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        buttonSobre.setOnClickListener {
            val intent = Intent(this, SobreActivity::class.java)
            startActivity(intent)
        }
    }
}