package com.example.sharewishes

import android.os.Bundle
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sharewishes.fragments.FavouriteFragment
import com.example.sharewishes.fragments.HomeFragment
import com.example.sharewishes.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar6)
        addFragmentToUi(HomeFragment::class.java)
        nav_view.setOnNavigationItemSelectedListener {
            if(it.itemId == R.id.navigation_home){
                if(frame_container.tag != null && frame_container.tag != HomeFragment::class.java.simpleName){
                    addFragmentToUi(HomeFragment::class.java)
                }
                return@setOnNavigationItemSelectedListener true
            }else if(it.itemId == R.id.navigation_favourite){
                if(frame_container.tag != null && frame_container.tag != FavouriteFragment::class.java.simpleName){
                    addFragmentToUi(FavouriteFragment::class.java)
                }
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun addFragmentToUi(className: Class<out Fragment>) {
        Utils.openFragment(supportFragmentManager,className,R.id.frame_container)
        frame_container.setTag(className.simpleName)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home , menu)
        return true
    }

}
