package br.com.coderup.pokemons.app.pokemons.presenters

import android.content.Context
import android.util.Log
import br.com.coderup.pokemons.PokemonsApplication
import br.com.coderup.pokemons.app.pokemons.PokemonsMvp
import br.com.coderup.pokemons.models.Pokemon
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class PokemonsPresenter : PokemonsMvp.Presenter {

    private var context : Context
    private var pokemonsView : PokemonsMvp.View? = null

    private var subscription : Subscription? = null


    private var pokemons : List<Pokemon> = Collections.emptyList()

    constructor(context: Context){
        this.context = context
    }

    /**
     * Presenter Methods
     */
    override fun loadPokemons(offset: Int) {

        pokemonsView?.showProgressIndicator()

        subscription?.unsubscribe()

        val service = PokemonsApplication
                .getContext(context)
                .getPokemonsServices()

        val pokemonsFromApi = service.listPokemons()
                .subscribeOn(Schedulers.io())

        subscription = Observable
                .zip(Observable.just(pokemons), pokemonsFromApi, { it, it2 -> it + it2 })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<Pokemon>>() {
                    override fun onNext(t: List<Pokemon>?) {
                        pokemons = t ?: emptyList<Pokemon>()
                    }

                    override fun onCompleted() {
                        pokemonsView?.showPokemons(pokemons)
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("Request error", e?.message)
                    }
                })
    }

    override fun onItemClicked(position: Int) {
        pokemonsView?.navigateToPokemonDetail(pokemons[position])
    }

    /**
     * Presenter lifecycler
     */

    override fun onDestroy(){
        subscription?.unsubscribe()
        subscription = null
        pokemonsView = null
    }

    override fun attachView(view: PokemonsMvp.View) {
        pokemonsView = view
    }
}
