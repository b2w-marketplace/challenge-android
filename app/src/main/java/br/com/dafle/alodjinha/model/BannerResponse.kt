package br.com.dafle.alodjinha.model

data class BannerResponse(val data: List<Banner>)

data class Banner(val id: Int, val linkUrl: String, val urlImagem: String)