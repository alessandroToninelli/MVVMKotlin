package it.toninelli.mvvmkotlin.Di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.view.View
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
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostViewModel(postsViewModel: PostsViewModel):ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory



}