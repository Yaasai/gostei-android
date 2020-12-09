package com.epicjames.oneshotrpg.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.epicjames.oneshotrpg.R
import kotlinx.android.synthetic.main.pedido_item_view.view.*

class PedidoItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    public val txtProdutoNome: TextView = itemView.findViewById(R.id.textViewProduto)
    public val txtQuantidade: TextView = itemView.findViewById(R.id.textViewQuantidade)
}