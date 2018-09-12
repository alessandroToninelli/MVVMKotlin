package it.toninelli.mvvmkotlin.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.toninelli.mvvmkotlin.Di.interfaces.Injectable


import it.toninelli.mvvmkotlin.R
import it.toninelli.mvvmkotlin.databinding.PostsFragmentBinding
import it.toninelli.mvvmkotlin.databinding.UserFragmentBinding
import it.toninelli.mvvmkotlin.util.AutoClearedValue
import it.toninelli.mvvmkotlin.util.autoclearedValue
import it.toninelli.mvvmkotlin.viewModel.PostsViewModel
import it.toninelli.mvvmkotlin.viewModel.ViewModelFactory
import javax.inject.Inject

class PostsFragment:Fragment(), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory


    var binding by autoclearedValue<PostsFragmentBinding>()

    private lateinit var viewModel: PostsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val dataBinding = DataBindingUtil.inflate<PostsFragmentBinding>(inflater,R.layout.posts_fragment,container,false)
        binding = dataBinding

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this,factory).get(PostsViewModel::class.java)
        viewModel.result.observe(this, Observer {
            binding.resource = it
        })


    }




}