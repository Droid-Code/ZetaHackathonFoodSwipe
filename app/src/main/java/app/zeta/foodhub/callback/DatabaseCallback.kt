package app.zeta.foodhub.callback

interface DatabaseCallback {
    fun onSuccess(model: Any)
    fun onError(e: Throwable)
}