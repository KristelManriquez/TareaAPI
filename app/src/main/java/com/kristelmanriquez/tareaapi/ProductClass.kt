package com.kristelmanriquez.tareaapi

data class ProductClass(
    val id: Long,
    val title: String,
    val description: String,
    val price:Long,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Long,
    val brand: String,
    val category:String,
    val thumbnail:String,
    val images: List<String>
)
