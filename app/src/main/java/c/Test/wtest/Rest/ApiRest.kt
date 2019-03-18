package c.Test.wtest.Rest

import c.Test.wtest.models.Consts
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiRest {

    private val okhttp = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

   private val retrofit = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttp)
            .build()

    fun zipCodeService(): ApiContract {
        return retrofit.create(ApiContract::class.java)
    }
}