package it.toninelli.mvvmkotlin.Di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import it.toninelli.mvvmkotlin.Di.component.DaggerAppComponent
import it.toninelli.mvvmkotlin.MVVMApp

class AppInjector{

    companion object {
        fun init(app:MVVMApp){
            DaggerAppComponent.builder().application(app).build().inject(app)
            app.registerActivityLifecycleCallbacks(object :Application.ActivityLifecycleCallbacks{
                override fun onActivityPaused(p0: Activity?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onActivityResumed(p0: Activity?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onActivityStarted(p0: Activity?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onActivityDestroyed(p0: Activity?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onActivityStopped(p0: Activity?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }


            })


        }

        fun run(callback : () -> Unit){
            print("caio")
            callback()
        }

        fun ciao(){
            run { prova() }
        }

        fun prova(){
            println("prova")
        }


    }
}