package c.Test.wtest.Rest

import c.Test.wtest.models.Zipcodes
import retrofit2.Call
import retrofit2.http.GET

interface ApiContract {

    @GET("codigos_postais.json")
    fun download(): Call<List<Zipcodes>>
}