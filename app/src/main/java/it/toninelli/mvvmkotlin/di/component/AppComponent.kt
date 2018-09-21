package it.toninelli.mvvmkotlin.di.component

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

import dagger.android.support.AndroidSupportInjectionModule
import it.toninelli.mvvmkotlin.di.interfaces.AppContext
import it.toninelli.mvvmkotlin.di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.di.module.AppModule
import it.toninelli.mvvmkotlin.di.module.SubComponentBuilder
import it.toninelli.mvvmkotlin.MVVMApp

@ApplicationScope
@Component(modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        SubComponentBuilder::class
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