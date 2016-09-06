package br.com.coderup.pokemons.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Pokemon : Serializable {

    @SerializedName("id") var id: Long? = null
    @SerializedName("name") var name: String? = null
    @SerializedName("thumbUrl") var thumbUrl: String? = null

}
