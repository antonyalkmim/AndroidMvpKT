package br.com.coderup.pokemons.app.pokemons.activities

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import br.com.coderup.pokemons.R
import br.com.coderup.pokemons.app.pokemons.PokemonsMvp
import br.com.coderup.pokemons.app.pokemons.adapters.PokemonsAdapter
import br.com.coderup.pokemons.app.pokemons.presenters.PokemonsPresenter
import br.com.coderup.pokemons.databinding.PokemonsActivityBinding
import br.com.coderup.pokemons.models.Pokemon

class PokemonsActivity : AppCompatActivity() , PokemonsMvp.View {

    private var presenter : PokemonsPresenter? = null
    private var binding : PokemonsActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.pokemons_activity)

        presenter = PokemonsPresenter()
        presenter!!.attachView(this)

        setupRecyclerView(binding?.pokemonsRecyclerView)

        presenter?.loadPokemons()
    }

    /**
     * Setups
     */
    private fun setupRecyclerView(recyclerView: RecyclerView?) {
        val llManager = LinearLayoutManager(this)

        val adapter = PokemonsAdapter(listOf())
        adapter.setCallback(object : PokemonsAdapter.Callback{
            override fun onItemClick(position: Int) {
                presenter?.onItemClicked(position)
            }
        })

        recyclerView?.layoutManager = llManager
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = adapter
    }

    /**
     * Mvp View methods
     */
    override fun showPokemons(pokemons: List<Pokemon>) {
        val adapter = binding!!.pokemonsRecyclerView.adapter as PokemonsAdapter
        adapter.setPokemons(pokemons)
    }

    override fun showProgressIndicator() {
        //TODO: show progressBar
    }

    override fun navigateToPokemonDetail(pokemon: Pokemon) {
        PokemonDetailActivity.navigateTo(this, pokemon)
    }

    override fun getContext(): Activity = this

}
