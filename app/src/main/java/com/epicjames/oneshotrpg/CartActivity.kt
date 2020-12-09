package com.epicjames.oneshotrpg

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epicjames.oneshotrpg.adapter.PedidosAdapter
import com.epicjames.oneshotrpg.model.Compra
import com.epicjames.oneshotrpg.model.Pedido
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cart.*


class CartActivity : AppCompatActivity() {
    private lateinit var pedidosAdapter: PedidosAdapter
    private var pedidos: MutableList<Pedido> = Pedido.registros
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        pedidosAdapter = PedidosAdapter(pedidos)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        CartRecyclerView.adapter = pedidosAdapter
        CartRecyclerView.layoutManager = layoutManager

        savedInstanceState?.clear()
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
        buttonFinalizar.setOnClickListener {
            if(pedidos.size > 0) {
                var tempString = ""
                var tempPreco: Double = 0.00
                pedidos.forEach {
                    tempString += it.produto.nome + "\n"
                    tempPreco += it.produto.preco * it.quantidade
                }
                tempString = tempString.substring(0,tempString.length-1)
                val user = FirebaseAuth.getInstance().currentUser?.uid.toString()
                val compraRef = FirebaseDatabase.getInstance().reference.child("usuarios").child(user).child("compras")
                compraRef.push().setValue(Compra(tempString,tempPreco))
                Pedido.limparRegistros()
                val intent = Intent(this, PerfilActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(outState != null)
            outState.clear()
    }
}