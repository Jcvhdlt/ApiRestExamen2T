package com.example.apirestexamen2t

data class ProductResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val results: List<Product>
)
