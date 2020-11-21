package app.zeta.foodhub.base


import android.app.Dialog
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import app.zeta.foodhub.R
import app.zeta.foodhub.data.remote.ApiServiceCall
import com.google.android.material.snackbar.Snackbar


@Suppress("DEPRECATION")
abstract class BaseActivity : AppCompatActivity() {

    protected val api = ApiServiceCall(Api.getApiServices())

    abstract fun enableFullScreen(): Boolean
    private var dialog: Dialog? = null
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus && enableFullScreen()) hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }


    protected fun notifyItemAdded(coordinatorLayout: CoordinatorLayout, message: String){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show()
    }

    protected fun showToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
    protected fun showProgress() {
        dialog = Dialog(this@BaseActivity)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.dialog_progress)
        dialog?.show()

    }

    protected fun hideProgress() {
        dialog?.dismiss()
    }
}