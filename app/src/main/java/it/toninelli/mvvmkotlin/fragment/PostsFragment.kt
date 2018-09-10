package it.toninelli.mvvmkotlin.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import it.toninelli.mvvmkotlin.Di.interfaces.Injectable


import it.toninelli.mvvmkotlin.R
import it.toninelli.mvvmkotlin.Util.findNavController
import it.toninelli.mvvmkotlin.ViewModel.PostsViewModel
import it.toninelli.mvvmkotlin.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.posts_fragment.view.*
import javax.inject.Inject

class PostsFragment:Fragment(), Injectable {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.posts_fragment,container,false)

        view.findViewById<TextView>(R.id.tw_pos).setOnClickListener { findNavController().navigate(R.id.user_fragment) }

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {


        super.onActivityCreated(savedInstanceState)


    }




}