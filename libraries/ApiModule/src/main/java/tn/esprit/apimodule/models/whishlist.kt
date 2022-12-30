package tn.esprit.apimodule.models

data class whishlist(
    val _id: String,
    val productId: Product? = null,
    val userId: User? = null
)
