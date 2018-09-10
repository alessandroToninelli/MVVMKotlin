package it.toninelli.mvvmkotlin.Di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.toninelli.mvvmkotlin.Di.interfaces.FragmentScope
import it.toninelli.mvvmkotlin.MainActivity
import it.toninelli.mvvmkotlin.fragment.PostsFragment
import it.toninelli.mvvmkotlin.fragment.UserFragment

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