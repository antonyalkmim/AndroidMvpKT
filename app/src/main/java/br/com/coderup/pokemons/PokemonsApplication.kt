package br.com.coderup.pokemons

import android.app.Application
import android.content.Context
import br.com.coderup.pokemons.networking.PokemonsService
import br.com.coderup.pokemons.utils.FrescoUtils
import rx.Scheduler
import rx.schedulers.Schedulers

class PokemonsApplication : Application() {

    var pokemonsService: PokemonsService? = null
    private var defaultScheduler : Scheduler? = null


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

    fun setPokemonsServices(pokemonsService : PokemonsService){
        this.pokemonsService = pokemonsService
    }

    fun getDefaultScheduler() : Scheduler {
        if (defaultScheduler == null){
            defaultScheduler = Schedulers.io()
        }
        return defaultScheduler!!
    }

    fun setDefaultScheduler(scheduler: Scheduler){
        defaultScheduler = scheduler
    }


    companion object {
        fun getContext(context: Context): PokemonsApplication {
            return context.applicationContext as PokemonsApplication
        }
    }

}
