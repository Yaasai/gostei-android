package com.epicjames.oneshotrpg.model

class Compras {
    val id: Int
    val pedido: String
    val preço: Double

    constructor(id: Int, pedido: String, preço: Double) {
        this.id = id
        this.pedido = pedido
        this.preço = preço
    }
}