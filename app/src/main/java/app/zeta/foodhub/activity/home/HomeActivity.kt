package app.zeta.foodhub.activity.home


import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.zeta.foodhub.R
import app.zeta.foodhub.activity.cart.CartActivity
import app.zeta.foodhub.adapter.FoodListAdapter
import app.zeta.foodhub.base.BaseActivity
import app.zeta.foodhub.callback.DatabaseCallback
import app.zeta.foodhub.callback.ResponseCallback
import app.zeta.foodhub.databinding.ActivityHomeBinding
import app.zeta.foodhub.manager.DatabaseManager
import app.zeta.foodhub.model.response.FoodResponse
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import swipeable.com.layoutmanager.OnItemSwiped
import swipeable.com.layoutmanager.SwipeableLayoutManager
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper


class HomeActivity : BaseActivity(), DatabaseCallback{

    private lateinit var binding: ActivityHomeBinding
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val adapter = FoodListAdapter(FoodResponse(), this)
    override fun enableFullScreen() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)


        val swipeableTouchHelperCallback =
            object : SwipeableTouchHelperCallback(object : OnItemSwiped {
                override fun onItemSwiped() {

                }

                override fun onItemSwipedLeft() {
                    adapter.removeItemAtTop()
                }

                override fun onItemSwipedRight() {
                    adapter.addItemToCart()

                }

                override fun onItemSwipedUp() {
                }

                override fun onItemSwipedDown() {
                }
            }) {
                override fun getAllowedSwipeDirectionsMovementFlags(viewHolder: RecyclerView.ViewHolder): Int {
                    return ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
                }
            }

        val itemTouchHelper = ItemTouchHelper(swipeableTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.recyclerView.layoutManager = SwipeableLayoutManager().setAngle(10)
            .setAnimationDuratuion(450)
            .setMaxShowCount(3)
            .setScaleGap(0.1f)
            .setTransYGap(0)
        binding.recyclerView.adapter = adapter

        loadRecipe()

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this@HomeActivity,CartActivity::class.java))
        }
    }

    private fun loadRecipe(){
        showProgress()
        compositeDisposable.add(api.getRecipeRequest(object: ResponseCallback{
            override fun onSuccess(any: Any) {
                adapter.udateItem(any as FoodResponse)
                hideProgress()
            }

            override fun onFailure(message: String) {
                notifyItemAdded(binding.coordinatorLayout,"No Internet Connection Available")
                hideProgress()
            }
        }))
    }



    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onSuccess(model: Any) {
        notifyItemAdded(binding.coordinatorLayout,"Item Added to Cart")
    }

    override fun onError(e: Throwable) {

    }
}