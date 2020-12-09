package com.epicjames.oneshotrpg

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.epicjames.oneshotrpg.model.Produto
import com.google.android.material.chip.Chip
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_produtos.*
import kotlinx.android.synthetic.main.card_produto.view.*
import java.util.*
import kotlin.collections.HashMap

class ProdutosActivity : AppCompatActivity() {
    val categorias: MutableList<String> = ArrayList()
    var categoriasSelecionadas: MutableList<String> = ArrayList()

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
        database.child("produtos")
            .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    val item = it.value as HashMap<*, *>
                    val categoria = item["categoria"] as String
                    if(categorias.indexOf(categoria) == -1) {
                        categorias.add(categoria)
                        adicionarCategoria(categoria)
                    }
                    adicionarProduto(item)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }
        })
    }

    private fun procurarProdutos(chave: String) {
        containerProdutos.removeAllViews()

        val searchInputToLower = chave.toLowerCase(Locale.ROOT)
        val searchInputTOUpper = chave.toUpperCase(Locale.ROOT)

        val database = FirebaseDatabase.getInstance().reference
        var prodRef = database.child("produtos")
        var query = prodRef
                    .orderByChild("nome")
                    .startAt(searchInputTOUpper)
                    .endAt("$searchInputToLower\uf8ff")
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    try {
                        val item = it.value as HashMap<*, *>
                        adicionarProduto(item)
                    } catch (e: Exception) {
                        Log.e("Erro ao obter produto", "Não foi possível converter o produto", e)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }
        })
    }

    private fun adicionarProduto(item: HashMap<*, *>) {
        if(categoriasSelecionadas.size == 0 || categoriasSelecionadas.indexOf(item["categoria"] as String) != -1) {
            // Criando produto
            val encodedString: String = item["imagem"] as String
            val pureBase64Encoded = encodedString.substring(encodedString.indexOf(",") + 1)
            val decodedString: ByteArray = Base64.decode(pureBase64Encoded, Base64.DEFAULT)
            val decodedByte: Bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            val produto = Produto(
                    item["nome"] as String,
                    item["descricao"] as String,
                    (item["preco"] as String).toFloat(),
                    decodedByte,
                    item["categoria"] as String
            )
            val rowItem = layoutInflater.inflate(R.layout.card_produto, containerProdutos, false)

            rowItem.textnome.text = produto.nome
            rowItem.textpreco.text = "R$ " + "%.2f".format(produto.preco)
            rowItem.textdescricao.text = produto.descricao
            rowItem.imagemproduto.setImageBitmap(produto.imagem)
            rowItem.product_card.setOnClickListener {
                val fm: FragmentManager = supportFragmentManager
                val dialog: ComprarFragment = ComprarFragment.newInstance(produto)
                dialog.show(fm, "fragment_comprar")
            }

            containerProdutos.addView(rowItem)
        }
    }

    fun adicionarCategoria(categoria: String) {
        val chip = Chip(this)
        chip.text = categoria
        chip.isCheckable = true
        chipGroup.addView(chip)
        chip.setOnClickListener {
            val index = categoriasSelecionadas.indexOf(categoria)
            if(index == -1) {
                categoriasSelecionadas.add(categoria)
            } else {
                categoriasSelecionadas.removeAt(index)
            }
            procurarProdutos(text_field_procura.text.toString())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}