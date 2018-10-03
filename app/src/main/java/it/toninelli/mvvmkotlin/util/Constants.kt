package it.toninelli.mvvmkotlin.util

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

const val BASE_URL_USER: String = "https://jsonplaceholder.typicode.com"

const val BASE_URL_POST = "https://www.reddit.com/"

var BASE_URL = BASE_URL_POST

fun Fragment.findNavController() : NavController = NavHostFragment.findNavController(this)
fun Activity.findNavController(@IdRes view: Int) : NavController = Navigation.findNavController(this,view)