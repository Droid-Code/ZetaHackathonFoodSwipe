package app.zeta.foodhub.data.remote

import app.zeta.foodhub.callback.ResponseCallback
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException

class ApiServiceCall(apiServices: ApiServices?) {
    private var apiServices: ApiServices? = null

    init {
        this.apiServices = apiServices
    }

    fun getRecipeRequest(callback: ResponseCallback?): Disposable? {
        return apiServices?.getRecipe()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                callback?.onSuccess(it)
            },{
                callback?.onFailure(getErrorMessage(it))
            })

    }


    private fun getErrorMessage(e: Any?): String {
        return if (e is HttpException?) {
            val exception = e as HttpException
            val message = (exception.response()?.errorBody() as ResponseBody)
            val jsonObject = JSONObject(message.string())
            jsonObject.getString("message").toString()
        } else {
            e.toString()
        }
    }

}