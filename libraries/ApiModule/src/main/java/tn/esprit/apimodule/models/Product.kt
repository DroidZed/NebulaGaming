package tn.esprit.apimodule.models

data class Product(
    val _id: String,
    val name: String,
    val image: String,
    val description: String,
    val status: String,
    val category: Category,
    val price: Float,
    val quantity: Int
)
