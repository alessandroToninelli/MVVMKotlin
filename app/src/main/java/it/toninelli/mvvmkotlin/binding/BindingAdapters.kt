package it.toninelli.mvvmkotlin.binding

import android.databinding.BindingAdapter
import android.view.View
import android.widget.Toast

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("showHide")
    fun showHide(view: View, b: Boolean){

        view.visibility = if(b) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("testProgress")
    fun testProgress(view: View, b: Boolean){
        println("test progress visibility $b")
        view.visibility = if(b) View.VISIBLE else View.GONE
    }


}