package com.mandarinkafe.mandarin.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandarinkafe.mandarin.databinding.FragmentMenuBinding
import com.mandarinkafe.mandarin.domain.Meal

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel: MenuViewModel by viewModels()
    private val menuAdapter: MenuAdapter by lazy {
        MenuAdapter(viewModel::onMealClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.rvMenu.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = menuAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMenuState().observe(viewLifecycleOwner)
        { state ->
            renderMenuScreen(state)
            hideProgressBar(state)
        }
        viewModel.getPlayerTrigger().observe(viewLifecycleOwner) { meal: Meal ->
            showMealDetails(meal)
        }

        viewModel.getMenu()
    }

    private fun showMealDetails(meal: Meal) {
        //TODO запуск фрагмента MealDetailsFragment
    }

    private fun renderMenuScreen(state: MenuScreenState) {
        when (state) {

            is MenuScreenState.Loading -> {
                showProgressBar()
            }

            MenuScreenState.NetworkError -> {
                placeholderManager(PlaceholderStatus.NO_NETWORK)
            }

            is MenuScreenState.Content -> {
                with(menuAdapter) {
                    submitList(state.mealList)
                    notifyDataSetChanged()
                }
            }
        }
    }

    private fun showProgressBar() {
        binding.searchProgressBar.isVisible = true

        placeholderManager(PlaceholderStatus.HIDDEN)
    }


    private fun hideProgressBar(state: MenuScreenState) {
        if (state != MenuScreenState.Loading) {
            binding.searchProgressBar.isVisible = false
        }
    }

    private fun placeholderManager(status: PlaceholderStatus) {
        binding.apply {
            when (status) {
                PlaceholderStatus.NO_NETWORK -> {
                    tvPlaceholderMessage.isVisible = true
                }

                PlaceholderStatus.HIDDEN -> {
                    tvPlaceholderMessage.isVisible = false
                }
            }
        }
    }

    private companion object {
        enum class PlaceholderStatus {
            NO_NETWORK,
            HIDDEN
        }
    }
}