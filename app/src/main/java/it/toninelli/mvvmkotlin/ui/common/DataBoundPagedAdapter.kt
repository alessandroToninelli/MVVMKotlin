package it.toninelli.mvvmkotlin.ui.common

import android.arch.paging.PagedListAdapter
import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import it.toninelli.mvvmkotlin.repository.NetworkState
import it.toninelli.mvvmkotlin.util.AppExecutors

abstract class DataBoundPagedAdapter<T, V: ViewDataBinding>(
        appExecutors: AppExecutors,
        diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, DataBoundViewHolder<V>>(
        AsyncDifferConfig.Builder<T>(diffCallback)
                .setBackgroundThreadExecutor(appExecutors.diskIO())
                .build()
) {


    protected var networkState: NetworkState? = null
    protected lateinit var binding: V

    abstract fun createBinding(parent: ViewGroup): V

    protected abstract fun bind(binding: V, item: T)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<V> {
        binding = createBinding(parent)
        return DataBoundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<V>, position: Int) {
        bind(holder.binding, getItem(position)!!)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    protected fun hasExtraRow() = networkState!= null && networkState != NetworkState.LOADED


      fun setLoadState(newNetworkState: NetworkState?): Boolean {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
         println("hadExtraRow $hadExtraRow")
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
         println("hasExtraRow $hasExtraRow")
        if (hadExtraRow != hasExtraRow) {
            println("valore di ritorno ${!hadExtraRow}")
            println("itemCount $itemCount")
            return !hadExtraRow
        } else {
            println("ramo else")
            return false
        }
    }
}