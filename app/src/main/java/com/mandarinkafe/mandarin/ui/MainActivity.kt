package com.mandarinkafe.mandarin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding) { "Binding wasn't initialized" }

    private var _appBarConfiguration: AppBarConfiguration? = null
    private val appBarConfiguration: AppBarConfiguration
        get() = requireNotNull(_appBarConfiguration) { "App Bar Configuration wasn't initialized" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUI()
    }

    private fun initializeUI() {
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val drawerLayout: DrawerLayout = binding.drawerLayoutMain
        val navView: NavigationView = binding.navView
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.container_view) as NavHostFragment? ?: return
        val navController = host.navController

        _appBarConfiguration = AppBarConfiguration(
            navController.graph, drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.container_view)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}