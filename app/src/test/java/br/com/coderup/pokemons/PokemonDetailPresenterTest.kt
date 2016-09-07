package br.com.coderup.pokemons

import br.com.coderup.pokemons.app.pokemons.PokemonDetailMvp
import br.com.coderup.pokemons.app.pokemons.PokemonsMvp
import br.com.coderup.pokemons.app.pokemons.presenters.PokemonDetailPresenter
import br.com.coderup.pokemons.app.pokemons.presenters.PokemonsPresenter
import br.com.coderup.pokemons.models.Pokemon
import br.com.coderup.pokemons.networking.PokemonsService
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import rx.Observable
import rx.schedulers.Schedulers


@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class PokemonDetailPresenterTest {

    var presenter : PokemonDetailPresenter? = null
    var view : PokemonDetailMvp.View? = null

    val pokemon = Pokemon(123, "Charmander", "char.jpg", "Fire Pokemon")

    @Before
    fun setup(){
        val application = RuntimeEnvironment.application as PokemonsApplication

        presenter = PokemonDetailPresenter(pokemon)
        view = Mockito.mock(PokemonDetailMvp.View::class.java)

        Mockito.`when`(view!!.getContext()).thenReturn(application)

        presenter!!.attachView(view!!)
    }

    @After
    fun tearDown(){
        presenter?.onDestroy()
    }

    @Test
    fun loadPokemonShouldCallsShowPokemon(){
        presenter?.loadPokemon()
        Mockito.verify(view)?.showPokemon(pokemon)
    }

}