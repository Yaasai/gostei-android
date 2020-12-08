package com.epicjames.oneshotrpg.model

import android.graphics.Bitmap

class Produto   {
    val nome: String
    val descricao: String
    val preco: Float
    val categoria: String
    val imagem: Bitmap

    constructor (nome: String, descricao: String, preco: Float, imagem: Bitmap, categoria: String) {
        this.nome = nome
        this.descricao = descricao
        this.preco = preco
        this.imagem = imagem
        this.categoria = categoria
    }
}