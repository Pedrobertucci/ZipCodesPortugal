package c.Test.wtest.ExerciseOne

import c.Test.wtest.models.Zipcodes
import retrofit2.Call
import retrofit2.http.GET

interface ZipCodeContract {

    @GET("codigos_postais.json")
    fun download(): Call<List<Zipcodes>>
}