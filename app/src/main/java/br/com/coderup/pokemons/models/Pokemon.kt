package br.com.coderup.pokemons.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Pokemon : Serializable {

    @SerializedName("id") var id: Long? = null
    @SerializedName("name") var name: String? = null
    @SerializedName("thumbUrl") var thumbUrl: String? = null
    @SerializedName("description") var description: String? = null


    constructor(id:Long, name : String, thumbUrl: String, description : String? = ""){
        this.id = id
        this.name = name
        this.thumbUrl = thumbUrl
        this.description = description
    }

}
