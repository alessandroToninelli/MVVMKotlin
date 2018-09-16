package it.toninelli.mvvmkotlin.ui.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.multibindings.IntKey
import it.toninelli.mvvmkotlin.Di.interfaces.Injectable
import it.toninelli.mvvmkotlin.R
import it.toninelli.mvvmkotlin.binding.RetryCallback
import it.toninelli.mvvmkotlin.databinding.UserFragmentBinding
import it.toninelli.mvvmkotlin.util.AppExecutors

import it.toninelli.mvvmkotlin.util.autoclearedValue
import javax.inject.Inject

class UserFragment: Fragment(), Injectable{


    var binding by autoclearedValue<UserFragmentBinding>()
    var adapter by autoclearedValue<UserListAdapter>()

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val dataBinding = DataBindingUtil.inflate<UserFragmentBinding>(inflater,R.layout.user_fragment,container,false)

        dataBinding.callback = object : RetryCallback {
            override fun retry() {
                println("retry")
            }
        }

        val userAdapter = UserListAdapter(appExecutors = appExecutors){
            println("user cliccato: $it")
        }

        adapter = userAdapter
        binding = dataBinding
        binding.userList.adapter = adapter
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this,factory).get(UserViewModel::class.java)
        val params = UserFragmentArgs.fromBundle(arguments)

        viewModel.loadUsers(params.idPost)

        initUserList()


    }

    private fun initUserList() {
        viewModel.result.observe(this, Observer {
            binding.resource = it
            adapter.submitList(it?.data)
        })
    }
}