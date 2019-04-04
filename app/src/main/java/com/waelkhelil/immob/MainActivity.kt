package com.waelkhelil.immob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
        private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_explore -> {
                findNavController(this, R.id.nav_host_fragment).navigate(R.id.ExploreFragment)
            }
            R.id.navigation_search -> {
                findNavController(this, R.id.nav_host_fragment).navigate(R.id.SearchFragment)
            }
            R.id.navigation_add-> {
                findNavController(this, R.id.nav_host_fragment).navigate(R.id.AddPostFragment)
            }
        }
        true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lBottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        lBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
