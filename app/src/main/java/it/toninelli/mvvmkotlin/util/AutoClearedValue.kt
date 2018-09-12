package it.toninelli.mvvmkotlin.util

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Classe per cancellare automaticamente alune proprietà del fragment che se venissero chiamate quando è distrutto causerebbero eccezzioni
 */


class AutoClearedValue<T: Any>(val fragment: Fragment) : ReadWriteProperty<Fragment,T> {

    private var value: T? = null

    init {
        fragment.lifecycle.addObserver(object : LifecycleObserver{
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy(){
                value = null
            }
        })
    }




    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return value?:throw IllegalStateException(
                "should never call auto-cleared-value get when it might not be available"
        )
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        this.value = value

    }





}


fun <T: Any> Fragment.autoclearedValue() = AutoClearedValue<T>(this)