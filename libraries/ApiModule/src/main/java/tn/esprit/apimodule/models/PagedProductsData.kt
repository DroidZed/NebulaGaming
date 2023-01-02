package tn.esprit.apimodule.models

data class PagedProductsData(
    val page: Int,
    val pageSize: Int,
    val total: Int,
    val pages: Int,
    val products: MutableList<Product>
)
