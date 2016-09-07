package br.com.coderup.pokemons.app.pokemons

import android.content.Context
import br.com.coderup.pokemons.models.Pokemon
import br.com.coderup.pokemons.mvp.Mvp

class PokemonsMvp {

    /**
     * Presenter
     */
    interface Presenter : Mvp.Presenter{
        fun loadPokemons(offset:Int = 1)
        fun onItemClicked(position : Int)
        fun attachView(view : PokemonsMvp.View)
    }

    /**
     * View
     */
    interface View : Mvp.View{
        fun showPokemons(pokemons : List<Pokemon>)
        fun showProgressIndicator()
        fun navigateToPokemonDetail(pokemon : Pokemon)
    }


}