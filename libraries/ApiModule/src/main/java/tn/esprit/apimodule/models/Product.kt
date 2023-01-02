package tn.esprit.apimodule.models

data class Product(
    val _id: String? = null,
    val name: String,
    val image: String? = null,
    val description: String,
    val status: Int,
    val category: Category? = null,
    val price: Float,
    val quantity: Int,
    val publisher: User? = null
)
