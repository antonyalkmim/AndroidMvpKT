package br.com.coderup.pokemons.app.pokemons

import br.com.coderup.pokemons.models.Pokemon

class PokemonsMvp {

    /**
     * Presenter
     */
    interface Presenter{
        fun loadPokemons(offset:Int = 1)
        fun onItemClicked(position : Int)
        fun onDestroy()
        fun attachView(view : PokemonsMvp.View)
    }

    /**
     * View
     */
    interface View{
        fun showPokemons(pokemons : List<Pokemon>)
        fun showProgressIndicator()
        fun navigateToPokemonDetail(pokemon : Pokemon)
    }


}