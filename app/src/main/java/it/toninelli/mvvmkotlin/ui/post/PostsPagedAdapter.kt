package it.toninelli.mvvmkotlin.ui.post

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import it.toninelli.mvvmkotlin.R
import it.toninelli.mvvmkotlin.databinding.PostItemBinding
import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.repository.NetworkState
import it.toninelli.mvvmkotlin.ui.common.DataBoundPagedAdapter
import it.toninelli.mvvmkotlin.util.AppExecutors


class PostsPagedAdapter(
        appExecutors: AppExecutors,
        private val callback: ((Post)-> Unit)
):DataBoundPagedAdapter<Post,PostItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Post>(){
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                println("oldItem: $oldItem e newItem $newItem")
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.body == newItem.body
            }

        }
){


    override fun createBinding(parent: ViewGroup): PostItemBinding {
       val binding = DataBindingUtil.inflate<PostItemBinding>(
               LayoutInflater.from(parent.context),
               R.layout.post_item,
               parent,
               false)
        binding.root.setOnClickListener {
            binding.post?.let {
                callback.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: PostItemBinding, item: Post) {
        binding.post = item

    }




}