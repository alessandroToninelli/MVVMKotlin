package it.toninelli.mvvmkotlin.Di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import it.toninelli.mvvmkotlin.Di.interfaces.ApplicationScope

@ApplicationScope
@Component(modules = arrayOf(
        AndroidInjectionModule::class
))
interface AppComponent{

}