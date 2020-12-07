package com.epicjames.oneshotrpg

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.epicjames.oneshotrpg.model.Produto
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_produtos.*
import kotlinx.android.synthetic.main.card_produto.view.*

class ProdutosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produtos)

        text_field_procura.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                procurarProdutos(text_field_procura.text.toString())
            }
            return@setOnEditorActionListener false
        }

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val database = FirebaseDatabase.getInstance().reference
        val produtosRef = database.child("produtos")
        produtosRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    val item = it.value as HashMap<*, *>
                    adicionarProduto(item)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                println(error!!.message)
            }
        })
    }

    fun procurarProdutos(chave: String) {
        containerProdutos.removeAllViews()

        val searchInputToLower = chave.toLowerCase();
        val searchInputTOUpper = chave.toUpperCase();
        val database = FirebaseDatabase.getInstance().reference
        database.child("produtos")
            .orderByChild("nome")
            .startAt(searchInputTOUpper)
            .endAt("$searchInputToLower\uf8ff")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        val item = it.value as HashMap<*, *>
                        adicionarProduto(item)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    println(error!!.message)
                }
            })
    }

    private fun adicionarProduto(item: HashMap<*, *>) {
        val rowItem = layoutInflater.inflate(R.layout.card_produto, containerProdutos, false)

        // Criando produto
        val encodedString: String = item.get("imagem") as String
        val pureBase64Encoded = encodedString.substring(encodedString.indexOf(",") + 1)
        val decodedString: ByteArray = Base64.decode(pureBase64Encoded, Base64.DEFAULT)
        val decodedByte: Bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        val produto: Produto = Produto(item.get("nome") as String, item.get("descricao") as String, item.get("preco") as Long, decodedByte)

        rowItem.textnome.text = produto.nome
        rowItem.textpreco.text = "R$ " + produto.preco.toString()
        rowItem.textdescricao.text = produto.descricao
        rowItem.imagemproduto.setImageBitmap(produto.imagem)

        rowItem.botaocomprar.setOnClickListener {

        }

        containerProdutos.addView(rowItem)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}