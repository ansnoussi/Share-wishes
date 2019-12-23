package com.example.sharewishes.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object Utils {

    fun openFragment(fragmentManager: FragmentManager , className: Class<out Fragment> , frameLayoutId : Int){
        val visibleFragment = visibleFragment(fragmentManager)
        val fragmentTransaction = fragmentManager.beginTransaction()
        if(visibleFragment != null){
            fragmentTransaction.hide(visibleFragment)
        }
        val replacedFragment = className.newInstance()
        fragmentTransaction.add(frameLayoutId,replacedFragment,className::class.java.simpleName)
        fragmentTransaction.commit()
    }

    private fun visibleFragment(fragmentManager: FragmentManager): Fragment? {
        val fragmentList = fragmentManager.fragments
        for(fragment in fragmentList){
            if(fragment!= null && fragment.isVisible){
                return fragment
            }
        }

        return null
    }

}