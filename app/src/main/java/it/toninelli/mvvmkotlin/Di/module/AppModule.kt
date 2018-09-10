package it.toninelli.mvvmkotlin.Di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import it.toninelli.mvvmkotlin.Di.interfaces.AppContext
import it.toninelli.mvvmkotlin.Di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.MVVMApp


@Module(includes = [NetModule::class, ViewModelModule::class])
abstract class AppModule{


    @ApplicationScope
    @Binds
    @AppContext
    abstract fun getContext(context: MVVMApp):Context


}