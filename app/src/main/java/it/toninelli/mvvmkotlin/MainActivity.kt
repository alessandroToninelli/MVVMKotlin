package it.toninelli.mvvmkotlin

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import it.toninelli.mvvmkotlin.Repository.PostRepo
import it.toninelli.mvvmkotlin.ViewModel.UserViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector{


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var postRepo: PostRepo

    @Inject
    lateinit var factory : ViewModelProvider.Factory


    private lateinit var viewModel : UserViewModel

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



        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel::class.java)
        viewModel.prova()


    }




//    override fun onSupportNavigateUp(): Boolean {
//
//        return findNavController(R.id.my_nav_host_fragment).navigateUp()
//    }


    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
