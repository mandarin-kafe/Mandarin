package com.mandarinkafe.mandarin

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.mandarinkafe.mandarin.cart.CartFragment
import com.mandarinkafe.mandarin.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()

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
        viewModel.getScreenState().observe(this@MainActivity) { state ->
            renderScreen(state)
        }
        viewModel.getMenu()
    }

    private fun renderScreen(state: MainViewModel.ScreenState) {
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        //TODO тут можно показывать экран загрузки, пока идут сетевые запросы. А потом уже показывать MenuFragment
        when (state) {
            is MainViewModel.ScreenState.Error -> {
                progressBar.isVisible = false
                Toast.makeText(
                    this,
                    "Ошибка выполнения сетевого запроса: ${state.errorMessage}", Toast.LENGTH_SHORT
                ).show()
            }

            is MainViewModel.ScreenState.Content -> {
                progressBar.isVisible = false
                Toast.makeText(
                    this,
                    "Запрос меню успешен!", Toast.LENGTH_SHORT
                ).show()
            }

            MainViewModel.ScreenState.Loading -> {
                progressBar.isVisible = true
            }
        }

    }

    private fun initializeUI() {
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val drawerLayout: DrawerLayout = binding.drawerLayoutMain
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.container_view) as NavHostFragment? ?: return
        val navController = host.navController

        _appBarConfiguration = AppBarConfiguration(
            navController.graph, drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.appBarMain.btCart.setOnClickListener {
            navController.navigate(
                R.id.cartFragment
            )
        }


        val headToolbar = binding.appBarMain.toolbar

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val window = window
            when (destination.id) {
                R.id.mealDetails -> {
                    headToolbar.visibility = View.GONE
                    window.statusBarColor =
                        ContextCompat.getColor(this, R.color.light_status_bar_color)
                    //TODO deprecated. Пока не поняла, чем заменить.

                }

                else -> {
                    headToolbar.visibility = View.VISIBLE

                    window.statusBarColor =
                        ContextCompat.getColor(this, R.color.default_status_bar_color)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.container_view)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    fun updateCartAdapter() {
        cartFragment?.displayCartItems()
    }
}