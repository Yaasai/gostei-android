package com.epicjames.oneshotrpg.model

import android.graphics.Bitmap

class Produto {
    var nome: String? = null
    var descricao: String? = null
    var preco: Long? = null
    var imagem: Bitmap? = null

    constructor(nome: String, descricao: String, preco: Long, imagem: Bitmap) {
        this.nome = nome
        this.descricao = descricao
        this.preco = preco
        this.imagem = imagem
    }
}