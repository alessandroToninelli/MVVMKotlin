package it.toninelli.mvvmkotlin.Model

import android.content.Context
import it.toninelli.mvvmkotlin.Di.interfaces.AppContext
import javax.inject.Inject



class Oggetto{
    init {
        println("chiamato init")
    }

    @Inject
    constructor(context: Context){
        println("costruttore con inject")
        println("$context")
    }

    fun getMessage(){
        println("print message")
    }
}