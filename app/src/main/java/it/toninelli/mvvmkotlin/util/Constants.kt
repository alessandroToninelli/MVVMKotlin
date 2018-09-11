package it.toninelli.mvvmkotlin.util

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

const val BASE_URL: String = "https://jsonplaceholder.typicode.com"


fun Fragment.findNavController() : NavController = NavHostFragment.findNavController(this)
fun Activity.findNavController(@IdRes view: Int) : NavController = Navigation.findNavController(this,view)