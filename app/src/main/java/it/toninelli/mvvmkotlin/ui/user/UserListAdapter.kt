package it.toninelli.mvvmkotlin.ui.user

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import it.toninelli.mvvmkotlin.R
import it.toninelli.mvvmkotlin.databinding.UserItemBinding
import it.toninelli.mvvmkotlin.model.User
import it.toninelli.mvvmkotlin.ui.common.DataBoundListAdapter
import it.toninelli.mvvmkotlin.util.AppExecutors

class UserListAdapter(
        appExecutors: AppExecutors,
        private val clickCallback: ((User) -> Unit)
): DataBoundListAdapter<User,UserItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
               return oldItem.address == newItem.address
            }

        }
){
    override fun createBinding(parent: ViewGroup): UserItemBinding {
        val binding = DataBindingUtil.inflate<UserItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.user_item,
                parent,
                false)
        binding.root.setOnClickListener{
            binding.user?.let {
                clickCallback.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: UserItemBinding, item: User) {
       binding.user = item
    }
}