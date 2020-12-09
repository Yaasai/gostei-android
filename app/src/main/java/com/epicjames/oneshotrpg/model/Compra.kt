package com.epicjames.oneshotrpg.model

class Compra {
    val pedido: String
    val preço: Double

    constructor(pedido: String, preço: Double) {
        this.pedido = pedido
        this.preço = preço
    }
}