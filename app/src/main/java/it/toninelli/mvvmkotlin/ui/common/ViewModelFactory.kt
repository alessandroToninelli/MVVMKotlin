package it.toninelli.mvvmkotlin.ui.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import it.toninelli.mvvmkotlin.di.interfaces.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider


@Suppress("UNCHECKED_CAST")
@ApplicationScope
class ViewModelFactory @Inject constructor(
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val creator = creators[modelClass] ?: creators.entries.firstOrNull{
                modelClass.isAssignableFrom(it.key)
            }?.value ?: throw IllegalArgumentException("unknow model class $modelClass")
        try {
            return creator.get() as T
        }catch (t: Throwable){
            throw RuntimeException(t)
        }
    }


}