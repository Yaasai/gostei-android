package com.epicjames.oneshotrpg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_compras.*
import kotlinx.android.synthetic.main.row_pedido.view.*

class ComprasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compras)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        refreshUI()
    }

    fun refreshUI(){
        container.removeAllViews()

        val userID  = FirebaseAuth.getInstance().currentUser?.uid

        val database = FirebaseDatabase.getInstance().reference
        val userRef = userID?.let {database.child("usuarios").child(it).child("compras")}
        userRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    val item = it.getValue() as HashMap<*, *>

                    val rowItem = layoutInflater.inflate(R.layout.row_pedido, container, false)
                    rowItem.textCompra.text = item.get("pedido") as String
                    rowItem.textPreco.text = "R$ " + "%.2f".format((item.get("preço")).toString().toFloat())

                    container.addView(rowItem)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}