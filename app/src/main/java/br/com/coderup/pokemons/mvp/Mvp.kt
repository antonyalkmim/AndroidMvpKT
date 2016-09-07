package br.com.coderup.pokemons.mvp

import android.content.Context

class Mvp {
    interface View {
        fun getContext() : Context
    }

    interface Presenter {
        fun onDestroy()
    }
}