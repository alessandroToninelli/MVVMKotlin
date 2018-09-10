package it.toninelli.mvvmkotlin.Di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import it.toninelli.mvvmkotlin.Di.interfaces.ViewModelKey
import it.toninelli.mvvmkotlin.ViewModel.*

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory



}