package it.toninelli.mvvmkotlin.ui.post

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import it.toninelli.mvvmkotlin.R
import it.toninelli.mvvmkotlin.databinding.PostItemBinding
import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.model.RedditPost
import it.toninelli.mvvmkotlin.repository.NetworkState
import it.toninelli.mvvmkotlin.ui.common.DataBoundPagedAdapter
import it.toninelli.mvvmkotlin.util.AppExecutors


class PostsPagedAdapter(
        appExecutors: AppExecutors,
        private val callback: ((RedditPost)-> Unit)
):DataBoundPagedAdapter<RedditPost,PostItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<RedditPost>(){
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem.title == newItem.title
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

    override fun bind(binding: PostItemBinding, item: RedditPost) {
        println(item.name)
        binding.post = item

    }




}