package app.zeta.foodhub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup


import androidx.recyclerview.widget.RecyclerView
import app.zeta.foodhub.callback.DatabaseCallback
import app.zeta.foodhub.databinding.ItemFoodBinding
import app.zeta.foodhub.manager.DatabaseManager
import app.zeta.foodhub.model.food.FoodCart
import app.zeta.foodhub.model.response.FoodResponse

class FoodListAdapter(var foodResponse: FoodResponse, var callback: DatabaseCallback) :
    RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val foodItemBinding = ItemFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FoodViewHolder(foodItemBinding)
    }

    override fun getItemCount(): Int = foodResponse.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foodResponse[position])
    }

    fun udateItem(foodItems: FoodResponse) {
        foodResponse.addAll(foodItems)
        notifyDataSetChanged()
    }

    fun removeItemAtTop() {
        var item = foodResponse.get(0)
        foodResponse.removeAt(0)
        foodResponse.add(item)
        notifyDataSetChanged()
    }

    fun addItemToCart() {
        var foodItem = foodResponse[0]
        DatabaseManager.instance.addFoodToCart(
            FoodCart(
                name = foodItem.name,
                category = foodItem.category,
                description = foodItem.description,
                image = foodItem.image,
                id = foodItem.id,
                label = foodItem.label,
                price = foodItem.price.toFloat()
            ),
            callback
        )
        //callback.onSuccess(foodResponse.get(0))
        foodResponse.removeAt(0)
        notifyDataSetChanged()
    }

}