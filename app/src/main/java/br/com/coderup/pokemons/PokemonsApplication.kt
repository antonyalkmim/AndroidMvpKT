package br.com.coderup.pokemons

import android.app.Application
import android.content.Context
import br.com.coderup.pokemons.networking.PokemonsService
import br.com.coderup.pokemons.utils.FrescoUtils

class PokemonsApplication : Application() {

    var pokemonsService: PokemonsService? = null

    override fun onCreate() {
        super.onCreate()
        FrescoUtils.initialize(this)
    }

    fun getPokemonsServices() : PokemonsService {
        if (pokemonsService == null){
            pokemonsService = PokemonsService.Factory.create(this)
        }
        return pokemonsService!!
    }

    companion object {
        fun getContext(context: Context): PokemonsApplication {
            return context.applicationContext as PokemonsApplication
        }
    }

}
