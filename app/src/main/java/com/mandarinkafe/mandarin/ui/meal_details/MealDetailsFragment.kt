package com.mandarinkafe.mandarin.ui.meal_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.bundle.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.gson.Gson
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.FragmentMealDetailsBinding
import com.mandarinkafe.mandarin.domain.models.Meal
import com.mandarinkafe.mandarin.domain.models.mockAdditionalsList
import org.koin.java.KoinJavaComponent.getKoin


class MealDetailsFragment : Fragment() {
    private val gson: Gson by lazy { Gson() }
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MealDetailsAdapter
    private lateinit var binding: FragmentMealDetailsBinding
    val meal by lazy {
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
        binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvAdditionals
        setupRecyclerView()
        val cornerRadius =
            resources.getDimensionPixelSize(R.dimen.image_corner_radius_2)

        binding.apply {
            tvMealTitle.text = meal.name
            tvMealIngredients.text = meal.description
            tvMealWeight.text = meal.weight.toString() + " г"

            Glide.with(requireContext())
                .load(meal.imageUrl)
                .centerCrop()
                .transform(RoundedCorners(cornerRadius))
                .placeholder(R.drawable.ic_cover_placeholder)
                .into(binding.ivMealPicture)
        }
    }


    private fun setupRecyclerView() {

        adapter = MealDetailsAdapter(mockAdditionalsList)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        recyclerView.adapter = adapter
    }

    companion object {
        const val MEAL = "meal"
        private val gson = Gson()
        fun createArgs(meal: Meal): Bundle =
            bundleOf(MEAL to gson.toJson(meal))
    }
}