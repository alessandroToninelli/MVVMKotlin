package it.toninelli.mvvmkotlin.Di

import android.app.Activity
import android.app.Application

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import it.toninelli.mvvmkotlin.Di.component.DaggerAppComponent
import it.toninelli.mvvmkotlin.Di.interfaces.Injectable
import it.toninelli.mvvmkotlin.MVVMApp


object AppInjector{
        fun init(app:MVVMApp){
            DaggerAppComponent.builder().application(app).build().inject(app)
            app.registerActivityLifecycleCallbacks(object :Application.ActivityLifecycleCallbacks{
                override fun onActivityStarted(p0: Activity?) {

                }

                override fun onActivityPaused(p0: Activity?) {
                }

                override fun onActivityResumed(p0: Activity?) {
                }



                override fun onActivityDestroyed(p0: Activity?) {
                }

                override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
                }

                override fun onActivityStopped(p0: Activity?) {
                }

                override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                    handleActivity(p0)
                }


            })


        }

        private fun handleActivity(p0: Activity?) {
            val activity = p0 ?: return
            if(activity is HasSupportFragmentInjector){
                println("$activity creata")
                AndroidInjection.inject(activity)
            }
            if (activity is FragmentActivity){
                activity.supportFragmentManager
                        .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks(){
                            override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {

                                if (f is Injectable)
                                    AndroidSupportInjection.inject(f)
                            }


                },true)
            }
        }


    }