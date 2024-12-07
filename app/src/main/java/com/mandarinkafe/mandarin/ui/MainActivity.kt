package com.mandarinkafe.mandarin.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.ActivityMainBinding
import com.mandarinkafe.mandarin.ui.cart.CartFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding) { "Binding wasn't initialized" }

    private var _appBarConfiguration: AppBarConfiguration? = null
    private val appBarConfiguration: AppBarConfiguration
        get() = requireNotNull(_appBarConfiguration) { "App Bar Configuration wasn't initialized" }

    private var cartFragment: CartFragment? = null
  
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
        binding.btnRight.setOnClickListener {
            val cartFragment = CartFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_view,cartFragment!!)
                .addToBackStack(null)
                .commit()
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController


        val headToolbar = binding.headToolbar

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val window = window
            when (destination.id) {
                R.id.mealDetails -> {
                    headToolbar.visibility = View.GONE
                    window.statusBarColor = ContextCompat.getColor(this, R.color.light_status_bar_color)
                    //TODO deprecated. Пока не поняла, чем заменить.

                }
                else -> {
                    headToolbar.visibility = View.VISIBLE
                    window.statusBarColor = ContextCompat.getColor(this, R.color.default_status_bar_color)
                }
            }
        }
    }
    fun updateCartAdapter() {
        cartFragment?.displayCartItems()
    }
}