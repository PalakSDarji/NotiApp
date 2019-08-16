package com.palak.notiapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.layout_fragment1.*

class Fragment1 : Fragment(){

    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("FRAGG : Fragment1 onCreate")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.layout_fragment1, container, false)
        println("FRAGG : Fragment1 onCreateView")
        return mView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("FRAGG : Fragment1 onViewCreated")

        btnFrag2.setOnClickListener {
            (activity as MainActivity).goToFragment2(true)
        }

        startNotif.setOnClickListener {
            (activity as MainActivity).startNotif()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("FRAGG : Fragment1 onDestroy")
    }





}