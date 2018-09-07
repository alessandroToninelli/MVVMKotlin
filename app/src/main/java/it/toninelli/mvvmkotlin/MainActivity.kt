package it.toninelli.mvvmkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import it.toninelli.mvvmkotlin.Model.Oggetto
import it.toninelli.mvvmkotlin.Model.Post
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var oggetto: Oggetto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Post = Post(2)
        oggetto.getMessage()

    }




    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
