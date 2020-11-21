package app.zeta.foodhub.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.zeta.foodhub.BR
import app.zeta.foodhub.databinding.ItemFoodBinding
import app.zeta.foodhub.databinding.ItemFoodCartBinding
import app.zeta.foodhub.model.food.FoodCart
import app.zeta.foodhub.model.response.FoodResponseItem

class FoodCartViewHolder(private val foodItemBinding: ItemFoodCartBinding) :
    RecyclerView.ViewHolder(foodItemBinding.root) {

    fun bind(foodItem: FoodCart){
        foodItemBinding.setVariable(BR.foodItem,foodItem)
        foodItemBinding.executePendingBindings()
    }
}