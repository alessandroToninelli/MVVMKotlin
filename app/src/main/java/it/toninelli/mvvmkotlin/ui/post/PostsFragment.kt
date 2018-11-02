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
import it.toninelli.mvvmkotlin.repository.NetworkState.Companion.LOADED
import it.toninelli.mvvmkotlin.repository.NetworkState.Companion.LOADING
import javax.inject.Inject
import  it.toninelli.mvvmkotlin.repository.NetworkState
import it.toninelli.mvvmkotlin.ui.post.PostsPagedAdapter
import java.util.concurrent.ThreadLocalRandom

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

        val postAdapter = PostsPagedAdapter(appExecutors = appExecutors) {
            //TODO I randomly generate an integer because I have a JSON in response that is from another site than the post
            findNavController().navigate(PostsFragmentDirections.postsToUser(ThreadLocalRandom.current().nextInt(1,10)))

        }

        binding.postList.adapter = postAdapter
        this.adapter = postAdapter

        initRefreshLayout()
        initPostList()

    }

    private fun initRefreshLayout() {
        viewModel.refreshState.observe(this, Observer {
            binding.swipeRefresh.isRefreshing = it == NetworkState.LOADING
        })

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initPostList() {
        viewModel.result.observe(this, Observer {
            binding.resource = it
            it?.data?.let {
                adapter.submitList(it)
            }
        })

        viewModel.networkState.observe(this, Observer {
            println(it)
            binding.netState = it
        })


    }


}