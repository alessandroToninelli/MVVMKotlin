package it.toninelli.mvvmkotlin.binding

import android.databinding.BindingAdapter
import android.view.View

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("showHide")
    fun showHide(view: View, b: Boolean){
        view.visibility = if(b) View.VISIBLE else View.GONE
    }

}