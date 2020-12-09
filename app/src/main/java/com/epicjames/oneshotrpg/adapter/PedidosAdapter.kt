package com.epicjames.oneshotrpg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.epicjames.oneshotrpg.R
import com.epicjames.oneshotrpg.model.Pedido
import com.epicjames.oneshotrpg.view.PedidoItemView

class PedidosAdapter(private var pedidos: MutableList<Pedido>) : RecyclerView.Adapter<PedidoItemView>() {

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): PedidoItemView {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.pedido_item_view, parent, false)
        val pedidoItemView: PedidoItemView = PedidoItemView(view)
        return pedidoItemView
    }

    override fun onBindViewHolder(@NonNull holder: PedidoItemView, position: Int) {
        val pedidoSelecionado: Pedido = pedidos.get(position)
        val produtoNome: String = pedidoSelecionado.produto.nome
        val produtoQuantidade: Int = pedidoSelecionado.quantidade
        // Causando o erro por aqui
        println(produtoNome)
        println(produtoQuantidade)
        holder.txtProdutoNome.setText(produtoNome)
        holder.txtQuantidade.setText(produtoQuantidade.toString())

    }

    override fun getItemCount(): Int {
        return pedidos.size
    }
}