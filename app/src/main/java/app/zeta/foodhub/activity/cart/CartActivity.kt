package app.zeta.foodhub.activity.cart


import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import app.zeta.foodhub.R
import app.zeta.foodhub.adapter.FoodCartListAdapter
import app.zeta.foodhub.base.BaseActivity
import app.zeta.foodhub.callback.DatabaseCallback
import app.zeta.foodhub.databinding.ActivityCartBinding
import app.zeta.foodhub.manager.DatabaseManager


class CartActivity : BaseActivity(), DatabaseCallback {
    override fun enableFullScreen() = false
    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)
        loadCart()
        binding.floatClear.setOnClickListener {
            DatabaseManager.instance.clearCart(this)
        }
    }

    private fun loadCart(){
        val foodCartItems  = DatabaseManager.instance.getAllCartList()
        binding.recyclerCartView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerCartView.adapter = FoodCartListAdapter(foodCartItems)
    }

    override fun onSuccess(model: Any) {
        showToast("Cart Cleared")
        loadCart()
    }

    override fun onError(e: Throwable) {

    }
}