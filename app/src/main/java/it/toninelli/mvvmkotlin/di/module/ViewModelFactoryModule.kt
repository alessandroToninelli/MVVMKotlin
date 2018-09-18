package it.toninelli.mvvmkotlin.di.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import it.toninelli.mvvmkotlin.ui.common.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {


    @Binds
    abstract fun getViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory




}