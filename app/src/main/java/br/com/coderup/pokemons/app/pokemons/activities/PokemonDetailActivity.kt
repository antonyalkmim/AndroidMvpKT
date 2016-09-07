package br.com.coderup.pokemons.app.pokemons.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View

import br.com.coderup.pokemons.R
import br.com.coderup.pokemons.app.pokemons.PokemonDetailMvp
import br.com.coderup.pokemons.app.pokemons.presenters.PokemonDetailPresenter
import br.com.coderup.pokemons.app.pokemons.presenters.PokemonsPresenter
import br.com.coderup.pokemons.databinding.PokemonDetailActivityBinding
import br.com.coderup.pokemons.databinding.PokemonsActivityBinding
import br.com.coderup.pokemons.models.Pokemon

class PokemonDetailActivity : AppCompatActivity(), PokemonDetailMvp.View {

    private var presenter : PokemonDetailPresenter? = null
    private var binding : PokemonDetailActivityBinding? = null


    companion object {
        private val POKEMON_KEY: String = "POKEMON_KEY"

        fun navigateTo(context: Context, pokemon : Pokemon){
            val intent = Intent(context, PokemonDetailActivity::class.java)
            intent.putExtra(PokemonDetailActivity.POKEMON_KEY, pokemon)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.pokemon_detail_activity)

        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = PokemonDetailPresenter(intent.getSerializableExtra(POKEMON_KEY) as Pokemon)
        presenter!!.attachView(this)

        presenter?.loadPokemon()
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.getItemId()) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    /**
     * Mvp View methods
     */
    override fun showPokemon(pokemon: Pokemon) {
        supportActionBar?.title = pokemon.name
        binding?.descriptionTv?.text = pokemon.description
    }

    override fun getContext(): Context  = this

}

