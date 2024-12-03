package com.mandarinkafe.mandarin.ui.meal_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.bundle.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.gson.Gson
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.FragmentMealDetailsBinding
import com.mandarinkafe.mandarin.domain.models.Meal
import com.mandarinkafe.mandarin.domain.models.mockAdditionalsList


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
    }

    private fun setMealData() {
        val cornerRadius =
            resources.getDimensionPixelSize(R.dimen.image_corner_radius_2)
        binding.apply {
            tvMealTitle.text = meal.name
            tvMealIngredients.text = meal.description
            tvMealWeight.text = getString(R.string.meal_weight_template, meal.weight)

            Glide.with(requireContext())
                .load(meal.imageUrl)
                .centerCrop()
                .transform(RoundedCorners(cornerRadius))
                .placeholder(R.drawable.ic_cover_placeholder)
                .into(ivMealPicture)

            fabAddToCartPrice.text = getString(R.string.meal_price_template, meal.price)
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
        val recyclerView = binding.rvAdditionals
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = MealDetailsAdapter(mockAdditionalsList)
    }

    companion object {
        const val MEAL = "meal"
        private val gson = Gson()
        fun createArgs(meal: Meal): Bundle =
            bundleOf(MEAL to gson.toJson(meal))
    }
}