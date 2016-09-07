package br.com.coderup.pokemons

import br.com.coderup.pokemons.app.pokemons.PokemonsMvp
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
class PokemonPresenterTest {

    var presenter : PokemonsPresenter? = null
    var view : PokemonsMvp.View? = null
    var service : PokemonsService? = null


    @Before
    fun setup(){
        val application = RuntimeEnvironment.application as PokemonsApplication
        service = Mockito.mock(PokemonsService::class.java)
        application.setPokemonsServices(service!!)
        application.setDefaultScheduler(Schedulers.immediate())


        presenter = PokemonsPresenter()
        view = Mockito.mock(PokemonsMvp.View::class.java)

        Mockito.`when`(view!!.getContext()).thenReturn(application)

        presenter!!.attachView(view!!)
    }

    @After
    fun tearDown(){
        presenter?.onDestroy()
    }

    @Test
    fun loadPokemonsShouldCallsShowPokemons(){

        val pokemons = listOf(Pokemon(123,"Charmander","charmander.jpg"),
                Pokemon(123, "Bulba", "bulba.jpg"))

        Mockito.`when`(service!!.listPokemons())
                .thenReturn(Observable.just(pokemons))

        presenter?.loadPokemons()

        Mockito.verify(view)?.showProgressIndicator()
        Mockito.verify(view)?.showPokemons(pokemons)
    }

}