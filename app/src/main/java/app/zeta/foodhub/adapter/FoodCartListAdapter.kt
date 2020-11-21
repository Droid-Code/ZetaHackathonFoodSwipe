package app.zeta.foodhub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup


import androidx.recyclerview.widget.RecyclerView
import app.zeta.foodhub.callback.DatabaseCallback
import app.zeta.foodhub.databinding.ItemFoodBinding
import app.zeta.foodhub.databinding.ItemFoodCartBinding
import app.zeta.foodhub.manager.DatabaseManager
import app.zeta.foodhub.model.food.FoodCart
import app.zeta.foodhub.model.response.FoodResponse

class FoodCartListAdapter(var foodResponse: List<FoodCart>) :
    RecyclerView.Adapter<FoodCartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCartViewHolder {
        val foodItemBinding = ItemFoodCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FoodCartViewHolder(foodItemBinding)
    }

    override fun getItemCount(): Int = foodResponse.size

    override fun onBindViewHolder(holder: FoodCartViewHolder, position: Int) {
        holder.bind(foodResponse[position])
    }


}