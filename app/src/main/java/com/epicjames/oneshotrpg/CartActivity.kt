package com.epicjames.oneshotrpg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.epicjames.oneshotrpg.model.Pedido
import com.firebase.ui.auth.data.model.User


class CartActivity : AppCompatActivity() {
    val pedidos: MutableList<Pedido> = Pedido.registros
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        if (intent.extras != null) {
            val pedido: Pedido = intent.getSerializableExtra("PEDIDO") as Pedido
            pedidos.add(pedido)
        }
        pedidos.forEach {
            println(it.produto.nome)
        }
    }
}