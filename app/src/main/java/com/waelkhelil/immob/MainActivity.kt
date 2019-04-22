package com.waelkhelil.immob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(this, R.id.nav_host_fragment)
        val lBottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        NavigationUI.setupWithNavController(toolbar, navController)
        NavigationUI.setupWithNavController(lBottomNavigationView, navController)
    }
}
