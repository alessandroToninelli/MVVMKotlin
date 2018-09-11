package it.toninelli.mvvmkotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.toninelli.mvvmkotlin.Di.interfaces.Injectable


import it.toninelli.mvvmkotlin.R

class PostsFragment:Fragment(), Injectable {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.posts_fragment,container,false)


        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }




}