package br.com.coderup.pokemons.app.pokemons.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.coderup.pokemons.R
import br.com.coderup.pokemons.databinding.PokemonsItemBinding
import br.com.coderup.pokemons.models.Pokemon
import br.com.coderup.pokemons.utils.FrescoUtils
import java.util.*

class PokemonsAdapter : RecyclerView.Adapter<PokemonsAdapter.PokemonViewHolder> {

    private var pokemons: List<Pokemon> = Collections.emptyList()
    private var callback: Callback? = null

    constructor(pokemons: List<Pokemon>) {
        this.pokemons = pokemons
    }

    fun setCallback(callback: Callback){
        this.callback = callback
    }

    fun setPokemons(newPokemons : List<Pokemon>){
        this.pokemons = newPokemons
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return pokemons.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PokemonViewHolder {
        val binding = DataBindingUtil.inflate<PokemonsItemBinding>(
                LayoutInflater.from(parent!!.context),
                R.layout.pokemons_item,
                parent,
                false)

        return PokemonViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PokemonViewHolder?, position: Int) {
        val binding = holder?.binding!!
        val pokemon = pokemons[position]

        //bind values
        binding.nameTf.text = pokemon.name
        binding.thumbIv.setImageURI(pokemon.thumbUrl)

        //bind events
        binding.pokemonCell.setOnClickListener {
            callback?.onItemClick(position)
        }


    }


    /**
     * PokemonViewHolder
     */
    class PokemonViewHolder(val binding: PokemonsItemBinding) :
            RecyclerView.ViewHolder(binding.pokemonCell)


    companion object interface Callback {
        fun onItemClick(position: Int)
    }

}