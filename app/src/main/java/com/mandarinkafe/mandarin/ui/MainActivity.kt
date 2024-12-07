package com.mandarinkafe.mandarin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
    }
    fun updateCartAdapter() {
        cartFragment?.displayCartItems()
    }
}