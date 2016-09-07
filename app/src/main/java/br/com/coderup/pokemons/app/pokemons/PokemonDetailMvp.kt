package br.com.coderup.pokemons.app.pokemons

import br.com.coderup.pokemons.models.Pokemon
import br.com.coderup.pokemons.mvp.Mvp

class PokemonDetailMvp {

    /**
     * Presenter
     */
    interface Presenter : Mvp.Presenter{
        fun loadPokemon()
        fun attachView(view : PokemonDetailMvp.View)
    }

    /**
     * View
     */
    interface View : Mvp.View{
        fun showPokemon(pokemon : Pokemon)
    }


}