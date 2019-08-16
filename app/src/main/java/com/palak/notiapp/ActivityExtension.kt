package com.palak.notiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun AppCompatActivity.addFragment(
    fragment: Fragment,
    frameId: Int,
    isAddToStack: Boolean,
    doAnimate: Boolean,
    bundle: Bundle?
) {

    val ft = supportFragmentManager.beginTransaction()

    if (bundle != null) {
        fragment.arguments = bundle
    }

    if (doAnimate) {

    }

    if (isAddToStack) {
        ft.addToBackStack(fragment.javaClass.name)
    }

    ft.add(frameId, fragment,fragment.javaClass.simpleName)
    ft.commit()
}


fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    isAddToStack: Boolean,
    doAnimate: Boolean,
    bundle: Bundle?
) {

    val ft = supportFragmentManager.beginTransaction()

    if (bundle != null) {
        fragment.arguments = bundle
    }

    if (doAnimate) {

    }

    if (isAddToStack) {
        ft.addToBackStack(fragment.javaClass.name)
    }

    ft.replace(frameId, fragment)
    ft.commit()
}