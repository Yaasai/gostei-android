package com.epicjames.oneshotrpg

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.epicjames.oneshotrpg.model.Pedido
import com.epicjames.oneshotrpg.model.Produto
import kotlinx.android.synthetic.main.fragment_comprar.*

class ComprarFragment : DialogFragment() {
    companion object {
        lateinit var produto: Produto
        fun newInstance(produto: Produto): ComprarFragment {
            this.produto = produto
            return ComprarFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comprar, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout( WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT )
        imagemproduto.setImageBitmap(produto.imagem)
        textnome.text = produto.nome
        textpreco.text = "R$ " + "%.2f".format(produto.preco)
        textdescricao.text = produto.descricao
        botaocarrinho.isEnabled = false

        textQuantidade.addTextChangedListener {
            try {
                val texto = it.toString()
                if(texto != "") {
                    val quantidade = textQuantidade.text.toString().toInt()
                    if(quantidade <= 0) {
                        textQuantidade.error = "Quantidade inválida"
                        botaocarrinho.isEnabled = false
                    } else {
                        textQuantidade.error = null
                        botaocarrinho.isEnabled = true
                    }
                } else {
                    botaocarrinho.isEnabled = false
                }
            } catch (e: NumberFormatException ) {
                textQuantidade.error = "Número inválido"
                botaocarrinho.isEnabled = false
            }
        }

        botaocarrinho.setOnClickListener {
            val nome = produto.nome
            val desc = produto.descricao
            val preco = produto.preco
            val categoria = produto.categoria
            val intent = Intent(activity,CartActivity::class.java)
            val produto = Produto(nome, desc, preco, null, categoria)
            val pedido = Pedido(produto, textQuantidade.text.toString().toInt())
            intent.putExtra("PEDIDO", pedido)
            startActivity(intent)
            dialog?.dismiss()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (outState != null)
            outState.clear()
    }
}