package c.Test.wtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Zipcodes(
    @SerializedName("num_cod_postal") val code: String,
    @SerializedName("ext_cod_postal") val extCode: String,
    @SerializedName("nome_localidade") val nameLocate: String,
    @SerializedName("nome_arteria") val title: String
) : Serializable