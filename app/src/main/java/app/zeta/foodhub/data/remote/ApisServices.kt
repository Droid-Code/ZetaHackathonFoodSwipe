package app.zeta.foodhub.data.remote

import app.zeta.foodhub.data.remote.ApiConstants.RECIPE_LIST
import app.zeta.foodhub.model.response.FoodResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiServices {


    @GET(RECIPE_LIST)
    fun getRecipe() : Observable<FoodResponse>

}