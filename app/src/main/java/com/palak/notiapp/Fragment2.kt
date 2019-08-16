package com.palak.notiapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.layout_fragment2.*

class Fragment2 : Fragment(){

    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("FRAGG : Fragment2 onCreate")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.layout_fragment2, container, false)
        println("FRAGG : Fragment2 onCreateView")
        return mView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("FRAGG : Fragment2 onViewCreated")

        btnFrag3.setOnClickListener {
            (activity as MainActivity).goToFragment3(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("FRAGG : Fragment2 onDestroy")
    }





}