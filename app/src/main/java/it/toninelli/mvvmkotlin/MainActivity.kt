package it.toninelli.mvvmkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import it.toninelli.mvvmkotlin.Repository.PostRepo
import it.toninelli.mvvmkotlin.Repository.UserRepo
import it.toninelli.mvvmkotlin.Util.Resource
import it.toninelli.mvvmkotlin.Util.Status
import it.toninelli.mvvmkotlin.Util.findNavController
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector{


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var postRepo: PostRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val disposable  = postRepo.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("data $it")
                    },
                        {
                    println("errore: $it")}
                )




    }



//    override fun onSupportNavigateUp(): Boolean {
//
//        return findNavController(R.id.my_nav_host_fragment).navigateUp()
//    }


    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
