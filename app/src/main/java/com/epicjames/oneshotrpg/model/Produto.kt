package com.epicjames.oneshotrpg.model

import android.graphics.Bitmap
import java.io.Serializable

class Produto : Serializable  {
    val nome: String
    val descricao: String
    val preco: Float
    val categoria: String
    @Transient val imagem: Bitmap

    constructor (nome: String, descricao: String, preco: Float, imagem: Bitmap, categoria: String) {
        this.nome = nome
        this.descricao = descricao
        this.preco = preco
        this.imagem = imagem
        this.categoria = categoria
    }
}