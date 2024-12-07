package com.mandarinkafe.mandarin.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.ActivityMainBinding
import com.mandarinkafe.mandarin.ui.cart.CartFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var cartFragment: CartFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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