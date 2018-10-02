package it.toninelli.mvvmkotlin.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.toninelli.mvvmkotlin.di.interfaces.Injectable


import it.toninelli.mvvmkotlin.R
import it.toninelli.mvvmkotlin.binding.RetryCallback
import it.toninelli.mvvmkotlin.databinding.PostsFragmentBinding
import it.toninelli.mvvmkotlin.util.autoclearedValue
import it.toninelli.mvvmkotlin.util.AppExecutors
import it.toninelli.mvvmkotlin.util.findNavController
import javax.inject.Inject

class PostsFragment:Fragment(), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var binding by autoclearedValue<PostsFragmentBinding>()
    var adapter by autoclearedValue<PostsPagedAdapter>()

    private lateinit var viewModel: PostsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val dataBinding = DataBindingUtil.inflate<PostsFragmentBinding>(inflater,R.layout.posts_fragment,container,false)
        binding = dataBinding

        dataBinding.callback = object : RetryCallback{
            override fun retry() {
                viewModel.retry()
            }
        }

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this,factory).get(PostsViewModel::class.java)


        val postAdapter = PostsPagedAdapter(appExecutors = appExecutors){
            findNavController().navigate(PostsFragmentDirections.postsToUser(it.userId))
        }


        binding.postList.adapter = postAdapter
        this.adapter = postAdapter


        initPostList()


    }

    private fun initPostList() {
        viewModel.result.observe(this, Observer {
            binding.resource = it
            println("resource ${it?.status}")
            it?.data?.let {
                adapter.submitList(it)
            }
        })

        viewModel.networkState.observe(this, Observer {
            println("networkState: ${it?.status}")
            binding.loadingMore = adapter.setLoadState(it)
        })
    }


}