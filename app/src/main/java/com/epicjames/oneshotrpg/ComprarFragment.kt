package com.epicjames.oneshotrpg

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.epicjames.carrinho.CartActivity
import com.epicjames.oneshotrpg.model.Pedido
import com.epicjames.oneshotrpg.model.Produto
import kotlinx.android.synthetic.main.fragment_comprar.*
import java.util.*

class ComprarFragment : DialogFragment() {

    companion object {
        private const val KEY_NOME = "KEY_NOME"
        private const val KEY_DESCRICAO = "KEY_DESCRICAO"
        private const val KEY_PRECO = "KEY_PRECO"
        private const val KEY_IMAGEM = "KEY_IMAGEM"
        lateinit var produto: Produto

        fun newInstance(produto: Produto): ComprarFragment {
            this.produto = produto
            val args = Bundle()
            args.putString(KEY_NOME, produto.nome)
            args.putString(KEY_DESCRICAO, produto.descricao)
            args.putFloat(KEY_PRECO, produto.preco)
            args.putParcelable(KEY_IMAGEM, produto.imagem)
            val fragment = ComprarFragment()
            fragment.arguments = args
            return fragment
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
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        imagemproduto.setImageBitmap(arguments?.getParcelable(KEY_IMAGEM))
        textnome.text = arguments?.getString(KEY_NOME)
        textpreco.text = "R$ " + arguments?.getFloat(KEY_PRECO).toString()
        textdescricao.text = arguments?.getString(KEY_DESCRICAO)

        botaocarrinho.setOnClickListener {
            // Adiciona ao carrinho
            // @Eiji
            val nome = produto.nome
            val desc = produto.descricao
            val preco = produto.preco
            val imagem = produto.imagem
            val categoria = produto.categoria
            val produto: Produto = Produto(nome,desc,preco,imagem, categoria)
            val pedido: Pedido = Pedido(produto, textQuantidade.text.toString().toInt())
            val intent = Intent(activity, CartActivity::class.java)
            intent.putExtra("PEDIDO", pedido)
            startActivity(intent)
        }
    }
}