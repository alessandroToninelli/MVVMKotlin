package it.toninelli.mvvmkotlin

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import it.toninelli.mvvmkotlin.Di.AppInjector
import javax.inject.Inject


class MVVMApp: Application(), HasActivityInjector  {


    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector


}