package it.toninelli.mvvmkotlin.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.toninelli.mvvmkotlin.di.interfaces.FragmentScope
import it.toninelli.mvvmkotlin.MainActivity
import it.toninelli.mvvmkotlin.ui.post.PostsFragment
import it.toninelli.mvvmkotlin.ui.user.UserFragment

@Module
 abstract class MainActivityModule {


    @FragmentScope
    @ContributesAndroidInjector
    abstract fun getPostsFragment(): PostsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun getUserFragment(): UserFragment

    @Binds
    abstract fun getContext(context: MainActivity):Context



}