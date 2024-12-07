package com.mandarinkafe.mandarin.ui.cart

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.FragmentCartBinding
import com.mandarinkafe.mandarin.domain.models.Meal
import com.mandarinkafe.mandarin.domain.models.mockPizzaList


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private lateinit var recommendedAdapter: RecommendedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupRecommendedRecyclerView()
        displayCartItems()
        binding.buttonOrder.setOnClickListener {
            sendEmail()
        }
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(Cart.items)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = cartAdapter
    }

    private fun setupRecommendedRecyclerView() {

        recommendedAdapter = RecommendedAdapter(mockPizzaList){ meal ->
            addToCart(meal) // добавляем обработчик нажатия
        }

        binding.recommendedRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recommendedRecyclerView.adapter = recommendedAdapter
    }

    fun displayCartItems() {
        cartAdapter.notifyDataSetChanged()
    }
    private fun addToCart(meal: Meal) {
        Cart.addItem(meal) // используем метод из Cart для добавления элемента
        cartAdapter.notifyItemInserted(Cart.items.size - 1) // уведомляем адаптер о добавлении
    }
    private fun sendEmail() {
        // Собираем информацию о товарах
        val cartItems = Cart.items
        val emailBody = StringBuilder("Уважаемый продавец, \n\nЯ хочу заказать следующие товары:\n\n")

        for (item in cartItems) {
            emailBody.append("${item.name} - ${item.weight} г, Цена: ${item.price} ₽\n")
        }

        emailBody.append("\nС уважением,\nТвой ждун")

        // Создаем Intent для отправки электронной почты
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // только email приложения
            putExtra(Intent.EXTRA_EMAIL, arrayOf("artemenko2014@mail.ru"))
            putExtra(Intent.EXTRA_SUBJECT, "Заказ товаров")
            putExtra(Intent.EXTRA_TEXT, emailBody.toString())
        }

        try {
            startActivity(emailIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Нет приложения для отправки почты", Toast.LENGTH_SHORT).show()
        }
    }

}