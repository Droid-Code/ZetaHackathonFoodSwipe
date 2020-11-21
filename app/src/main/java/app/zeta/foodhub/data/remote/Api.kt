import app.zeta.foodhub.BuildConfig
import app.zeta.foodhub.data.remote.ApiServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {

    private var apiServices: ApiServices? = null

    private var httpClient: OkHttpClient.Builder? = null

    init {
        getRetrofitClient()
    }
    /**
     * To build retrofit object
     */
    private fun getRetrofitClient() : Retrofit{
        httpClient = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            readTimeout(5000, TimeUnit.SECONDS)
            writeTimeout(5000, TimeUnit.SECONDS)
            connectTimeout(5000, TimeUnit.SECONDS)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient!!.build())
            .build()

        apiServices = retrofit.create(ApiServices::class.java)
        return retrofit
    }

    fun getApiServices() = apiServices
}