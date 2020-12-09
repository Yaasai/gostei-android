package com.epicjames.oneshotrpg.model

import java.io.Serializable

class Pedido: Serializable {
    val produto: Produto
    val quantidade: Int

    constructor (produto: Produto, quantidade: Int) {
        this.produto = produto
        this.quantidade = quantidade
    }

    companion object pedidos {
        var registros: MutableList<Pedido> = mutableListOf<Pedido>()

        fun limparRegistros() {
            this.registros =  mutableListOf()
        }
    }
}