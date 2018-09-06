package it.toninelli.mvvmkotlin.Di.component

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import it.toninelli.mvvmkotlin.Di.interfaces.AppContext
import it.toninelli.mvvmkotlin.Di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.Di.module.AppModule
import it.toninelli.mvvmkotlin.MVVMApp

@ApplicationScope
@Component(modules = [
        AndroidInjectionModule::class,
        AppModule::class
        ])
interface AppComponent{

   @Component.Builder
    interface Builder{
       @BindsInstance
       fun application(app: MVVMApp):Builder
       fun build():AppComponent
   }

    fun inject(mvvmApp: MVVMApp)

}