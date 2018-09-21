package it.toninelli.mvvmkotlin.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.toninelli.mvvmkotlin.di.interfaces.ActivityScope
import it.toninelli.mvvmkotlin.di.interfaces.AppContext
import it.toninelli.mvvmkotlin.MainActivity


@Module
abstract class SubComponentBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    abstract fun getMainActivity():MainActivity






}