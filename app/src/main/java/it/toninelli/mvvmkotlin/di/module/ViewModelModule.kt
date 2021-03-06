package it.toninelli.mvvmkotlin.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import it.toninelli.mvvmkotlin.di.interfaces.ViewModelKey
import it.toninelli.mvvmkotlin.ui.common.ViewModelFactory
import it.toninelli.mvvmkotlin.ui.post.PostsViewModel
import it.toninelli.mvvmkotlin.ui.user.UserViewModel

@Module(includes = [ViewModelFactoryModule::class])
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostViewModel(postsViewModel: PostsViewModel):ViewModel





}