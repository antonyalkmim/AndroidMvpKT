package br.com.coderup.pokemons.networking

import android.content.Context
import br.com.coderup.pokemons.R
import br.com.coderup.pokemons.models.Pokemon
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable

interface PokemonsService {

    @GET("/pokemons.json")
    fun listPokemons(): Observable<List<Pokemon>>


    object Factory {

        fun create(context: Context): PokemonsService {
                val retrofit = Retrofit.Builder()
                        .baseUrl(context.getString(R.string.api_url))
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build()
                return retrofit.create(PokemonsService::class.java)
            }
    }

}