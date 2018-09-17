package it.toninelli.mvvmkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector{


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val repeatFun: String.(Int) -> String = {this.repeat(it)}

        val l = foo(2){
            "$it nel lambda"
        }
        println(l)


//        val sum = {
//            x: Int, y: Int -> println("chiamato lambda");x+y
//        }
//
//        val sumfoo = fun Int.(other: Int):Int = this+other
//        val sumfoo2: Int.(Int)-> Int = {
//            this+it
//        }
//
//        println(sum(2,3))
//        println(2.sumfoo(3))
//        println(2.sumfoo2(3))



    }




    fun foo(l: Int, f: (Int) -> String ) : String{
        val ritorno =  f(l)
        println("valore in foo $ritorno")
        return "eseguito"
    }


    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
