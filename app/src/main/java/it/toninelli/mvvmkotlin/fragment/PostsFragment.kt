package it.toninelli.mvvmkotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



import it.toninelli.mvvmkotlin.R
import it.toninelli.mvvmkotlin.Util.findNavController
import kotlinx.android.synthetic.main.posts_fragment.view.*

class PostsFragment:Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.posts_fragment,container,false)


        return view
    }





}