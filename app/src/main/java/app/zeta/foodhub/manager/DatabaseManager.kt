package app.zeta.foodhub.manager

import app.zeta.foodhub.FoodHubApplication
import app.zeta.foodhub.callback.DatabaseCallback
import app.zeta.foodhub.model.food.Food
import app.zeta.foodhub.model.food.FoodCart
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DatabaseManager {
    companion object {
        val instance = DatabaseManager()
        private val foodDao = FoodHubApplication.dbInstance.foodDao()
        private val foodCart= FoodHubApplication.dbInstance.foodCartDao()
    }

    fun addFoodToCart(foodCartItem: FoodCart, callback: DatabaseCallback?) {
        foodCart
            .insertFood(foodCartItem)
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    callback?.onSuccess(foodCartItem)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    callback?.onError(e)
                }

            })

    }


    fun getAllCartList() = foodCart.getAll()

    fun clearCart(callback: DatabaseCallback?){
        foodCart.deleteAll()
        callback?.onSuccess(1)
    }
}