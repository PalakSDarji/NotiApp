package com.palak.notiapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment3 : Fragment(){

    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("FRAGG : Fragment3 onCreate")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.layout_fragment3, container, false)
        println("FRAGG : Fragment3 onCreateView")
        return mView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("FRAGG : Fragment3 onViewCreated")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("FRAGG : Fragment3 onDestroy")
    }

}