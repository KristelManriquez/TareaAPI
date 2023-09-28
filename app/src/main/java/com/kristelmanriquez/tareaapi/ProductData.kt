package com.kristelmanriquez.tareaapi

data class ProductData (
    val products: List<ProductClass>,
    val total: Long,
    val skip: Long,
    val limit: Long
)
