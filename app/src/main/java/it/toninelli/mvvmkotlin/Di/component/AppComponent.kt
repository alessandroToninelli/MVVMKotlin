package it.toninelli.mvvmkotlin.Di.component

import android.content.Context
import dagger.Binds
import dagger.Component
import dagger.android.AndroidInjectionModule
import it.toninelli.mvvmkotlin.Di.interfaces.AppContext
import it.toninelli.mvvmkotlin.Di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.MVVMApp

@ApplicationScope
@Component(modules = [
        AndroidInjectionModule::class
        ])
interface AppComponent{

    @ApplicationScope
    @Binds
    @AppContext
     fun getContext(app: MVVMApp): Context

}