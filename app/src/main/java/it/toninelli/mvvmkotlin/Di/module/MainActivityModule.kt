package it.toninelli.mvvmkotlin.Di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import it.toninelli.mvvmkotlin.MainActivity

@Module
 abstract class MainActivityModule {



    @Binds
    abstract fun getContext(context: MainActivity):Context
}