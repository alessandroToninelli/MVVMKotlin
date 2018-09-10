package it.toninelli.mvvmkotlin.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.toninelli.mvvmkotlin.Di.interfaces.Injectable
import it.toninelli.mvvmkotlin.R
import it.toninelli.mvvmkotlin.ViewModel.PostsViewModel
import it.toninelli.mvvmkotlin.ViewModel.UserViewModel
import javax.inject.Inject

class UserFragment: Fragment(), Injectable{



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.user_fragment,container,false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




    }
}