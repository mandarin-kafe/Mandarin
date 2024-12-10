package com.mandarinkafe.mandarin.meal_details.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.bundle.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.gson.Gson
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.FragmentMealDetailsBinding
import com.mandarinkafe.mandarin.menu.domain.models.Meal
import com.mandarinkafe.mandarin.menu.domain.models.mockAdditionalsList


class MealDetailsFragment : Fragment() {
    private val gson: Gson by lazy { Gson() }
    private var _binding: FragmentMealDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MealDetailsViewModel by viewModels()
    private val meal by lazy {
        gson.fromJson(
            requireArguments().getString(MEAL),
            Meal::class.java
        )
    }
    private var mealPrice = 0

    private var addsCategoriesPizza = arrayListOf<String>(
        "МЯСО", "СЫР", "ОВОЩИ", "РЫБА И МОРЕПРОДУКТЫ"
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setMealData()
        setTabs(addsCategoriesPizza)
    }

    private fun setMealData() {
        val cornerRadius =
            resources.getDimensionPixelSize(R.dimen.image_corner_radius_2)
        mealPrice = meal.price
        binding.apply {
            tvMealTitleTop.text = meal.name
            tvMealIngredients.text = meal.description
            tvMealWeight.text = getString(R.string.meal_weight_template, meal.weight)
            tvMealPriceOriginal.text = getString(R.string.meal_price_template, meal.price)


            Glide.with(requireContext())
                .load(meal.imageUrl)
                .centerCrop()
                .transform(RoundedCorners(cornerRadius))
                .placeholder(R.drawable.ic_cover_placeholder)
                .into(ivMealPicture)

            fabAddToCartPrice.text = getString(
                R.string.meal_price_template,
                mealPrice
            )


            ivMealPicture.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
            ivMealPicture.animate()

            ibBack.setOnClickListener {
                findNavController().navigateUp()
            }
            fabAddToCartPrice.setOnClickListener {
                onCartButtonClick()
            }
            ivAddToFavorite.setOnClickListener {
                onFavoriteToggleClick()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onCartButtonClick() {
        Toast.makeText(
            requireContext(),
            "Добавляю в корзину ${meal.name}, ${meal.price} ₽",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onFavoriteToggleClick() {
        viewModel.toggleFavorite(meal)
        Toast.makeText(
            requireContext(),
            "Тык на сердечко: ${meal.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvAdds
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = MealAdditionalsAdapter(
            mockAdditionalsList,
            object : MealAdditionalsAdapter.AddsClickListener {

                override fun plusToCartClick(additional: Meal) {
                    //TODO сделать логику корзины для добавок к блюду
                    mealPrice += additional.price
                    binding.fabAddToCartPrice.text =
                        getString(R.string.meal_price_template, mealPrice)

                }

                override fun minusToCartClick(additional: Meal) {
                    //TODO сделать логику корзины для добавок к блюду
                    mealPrice -= additional.price
                    binding.fabAddToCartPrice.text =
                        getString(R.string.meal_price_template, mealPrice)
                }
            })
    }

    private fun setTabs(addsCategories: ArrayList<String>) {
        val tabLayout = binding.tabLayoutAddsCategories

        if (addsCategories.isEmpty()) {
            Log.e("DEBUG", "Список подкатегорий пуст!")
            return
        }
        addsCategories.forEach { addsCategory ->
            tabLayout.addTab(tabLayout.newTab().setText(addsCategory))
        }


    }

    companion object {
        const val MEAL = "meal"
        private val gson = Gson()
        fun createArgs(meal: Meal): Bundle =
            bundleOf(MEAL to gson.toJson(meal))
    }
}