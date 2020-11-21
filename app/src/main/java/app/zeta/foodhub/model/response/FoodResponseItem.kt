package app.zeta.foodhub.model.response

data class FoodResponseItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val label: String,
    val name: String,
    val price: String
)