package br.com.coderup.pokemons.app.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.coderup.pokemons.app.pokemons.activities.PokemonsActivity

class SplashActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this,  PokemonsActivity::class.java))
        finish()
    }

}
