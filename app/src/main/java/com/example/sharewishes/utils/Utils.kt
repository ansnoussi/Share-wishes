package com.example.sharewishes.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object Utils {

    fun openFragment(fragmentManager: FragmentManager , className: Class<out Fragment> , frameLayoutId : Int){
        if(!isFragmentAlreadyAdded(fragmentManager, className)){
        val visibleFragment = visibleFragment(fragmentManager)
        val fragmentTransaction = fragmentManager.beginTransaction()
        if(visibleFragment != null){
            fragmentTransaction.hide(visibleFragment)
        }
        val replacedFragment = className.newInstance()
        fragmentTransaction.add(frameLayoutId,replacedFragment,className::class.java.simpleName)
        fragmentTransaction.commit()}
        else{
            showFragment(fragmentManager, className)
        }
    }

    private fun showFragment(fragmentManager: FragmentManager, className: Class<out Fragment>) {
        val fragmentList = fragmentManager.fragments
        val fragmentTransaction = fragmentManager.beginTransaction()
        for(fragment in fragmentList){
            if(fragment != null && fragment.javaClass.simpleName == className.simpleName){
                fragmentTransaction.show(fragment)
            }else{
                fragmentTransaction.hide(fragment)
            }
        }
        fragmentTransaction.commit()
    }

    private fun isFragmentAlreadyAdded(
        fragmentManager: FragmentManager,
        className: Class<out Fragment>
    ): Boolean {
        val fragmentList = fragmentManager.fragments
        return fragmentList.any { it != null && it.javaClass.simpleName == className.simpleName }
    }

    private fun visibleFragment(fragmentManager: FragmentManager): Fragment? {
        val fragmentList = fragmentManager.fragments

        return fragmentList.firstOrNull { it != null && it.isVisible }
    }

}