package it.toninelli.mvvmkotlin.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import it.toninelli.mvvmkotlin.di.interfaces.AppContext
import it.toninelli.mvvmkotlin.di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.MVVMApp


@Module(includes = [NetModule::class, ViewModelModule::class])
abstract class AppModule{


    @ApplicationScope
    @Binds
    @AppContext
    abstract fun getContext(context: MVVMApp):Context


}