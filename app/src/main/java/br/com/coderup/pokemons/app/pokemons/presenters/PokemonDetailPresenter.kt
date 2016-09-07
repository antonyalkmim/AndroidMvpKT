package br.com.coderup.pokemons.app.pokemons.presenters

import br.com.coderup.pokemons.app.pokemons.PokemonDetailMvp
import br.com.coderup.pokemons.models.Pokemon

class PokemonDetailPresenter : PokemonDetailMvp.Presenter {

    val pokemon : Pokemon
    var view : PokemonDetailMvp.View ? = null

    constructor(pokemon : Pokemon){
        this.pokemon = pokemon
    }

    override fun loadPokemon() {
        view?.showPokemon(pokemon)
    }

    override fun attachView(view: PokemonDetailMvp.View) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
    }

}