package app.zeta.foodhub.adapter


import androidx.recyclerview.widget.RecyclerView
import app.zeta.foodhub.BR
import app.zeta.foodhub.databinding.ItemFoodBinding
import app.zeta.foodhub.model.food.Food

class FoodViewHolder(private val foodItemBinding: ItemFoodBinding) :
    RecyclerView.ViewHolder(foodItemBinding.root) {

    fun bind(foodItem: Food){
        foodItemBinding.setVariable(BR.foodItem,foodItem)
        foodItemBinding.executePendingBindings()
    }
}