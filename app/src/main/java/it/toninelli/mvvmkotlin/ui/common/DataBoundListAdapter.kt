package it.toninelli.mvvmkotlin.ui.common

import android.databinding.OnRebindCallback
import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import it.toninelli.mvvmkotlin.util.AppExecutors


abstract class DataBoundListAdapter<T, V: ViewDataBinding>(
        appExecutors: AppExecutors,
        diffCallback: DiffUtil.ItemCallback<T>
): ListAdapter<T, DataBoundViewHolder<V>>(
        AsyncDifferConfig.Builder<T>(diffCallback)
                .setBackgroundThreadExecutor(appExecutors.diskIO())
                .build()
){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<V> {
        val binding = createBinding(parent)
        return DataBoundViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DataBoundViewHolder<V>, position: Int) {
        bind(holder.binding, getItem(position))
    }
    abstract fun createBinding(parent: ViewGroup): V
    protected abstract fun bind(binding: V, item: T)
}