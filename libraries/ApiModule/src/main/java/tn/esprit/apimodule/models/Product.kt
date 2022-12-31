package tn.esprit.apimodule.models

data class Product(
    val _id: String? = null,
    val name: String,
    val image: String,
    val description: String,
    val status: String,
    val category: Category? = null,
    val price: Float,
    val quantity: Int
)
