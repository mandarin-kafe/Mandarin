package com.mandarinkafe.mandarin.ui.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.FragmentMenuBinding
import com.mandarinkafe.mandarin.domain.models.Meal
import com.mandarinkafe.mandarin.domain.models.MenuCategory
import com.mandarinkafe.mandarin.domain.models.MenuItem
import com.mandarinkafe.mandarin.domain.models.mockMenuData
import com.mandarinkafe.mandarin.ui.menu.MenuViewModel.MenuScreenState

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var tabLayout: TabLayout
    private val recyclerView: RecyclerView by lazy { binding.rvMenu }
    private var isClickAllowed = true
    private val handler = Handler(Looper.getMainLooper())
    private val viewModel: MenuViewModel by viewModels()
    private var menuItems = mutableListOf<MenuItem>()
    private var menuCategories = arrayListOf<MenuCategory>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        tabLayout = binding.tabLayout

        menuCategories = mockMenuData // TODO убрать это!!!

        menuCategories.forEach { category ->
            menuItems.add(MenuItem.Header(category.name))
            menuItems.addAll(category.items.map { MenuItem.MealItem(it) })
            // TODO убрать это!!!
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.prepareMenuItems()
        viewModel.getMenuState().observe(viewLifecycleOwner)
        { state ->
            renderMenuScreen(state)
        }
        setupScrollSync()
        setupTabs()

    }

    private fun setupTabs() {
        menuCategories.forEach { category ->
            tabLayout.addTab(tabLayout.newTab().setText(category.name))
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position ?: 0
                scrollToCategory(position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun scrollToCategory(position: Int) {
        val headerPosition = menuItems.indexOfFirst {
            it is MenuItem.Header && it.categoryName == menuCategories[position].name
        }
        if (headerPosition != -1) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val offset = resources.getDimensionPixelSize(R.dimen.recycler_view_offset)
            layoutManager.scrollToPositionWithOffset(headerPosition, offset)
        }
    }

    private fun setupScrollSync() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()

                if (firstVisiblePosition != RecyclerView.NO_POSITION) {
                    val firstVisibleItem = menuItems[firstVisiblePosition]
                    if (firstVisibleItem is MenuItem.Header) {
                        val headerIndex =
                            menuCategories.indexOfFirst { it.name == firstVisibleItem.categoryName }
                        if (headerIndex != -1 && headerIndex != tabLayout.selectedTabPosition) {
                            tabLayout.setScrollPosition(headerIndex, 0f, true)
                        }
                    }
                }
            }
        })
    }


    private fun showMealDetails(meal: Meal) {
        //TODO запуск фрагмента MealDetailsFragment
    }


    private fun renderMenuScreen(state: MenuScreenState) {
        when (state) {
            is MenuScreenState.Loading -> {
                progressBarManager(VisibilityStatus.VISIBLE)
            }

            is MenuScreenState.NetworkError -> {
                placeholderManager(VisibilityStatus.VISIBLE)

            }

            is MenuScreenState.Content -> {
                progressBarManager(VisibilityStatus.HIDDEN)
                placeholderManager(VisibilityStatus.HIDDEN)
//                menuItems = state.menuItems
//                menuCategories = state.menuCategories
                setRvAdapter()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRvAdapter(): MenuAdapter {
        val menuAdapter = MenuAdapter(menuItems,
            object : MenuAdapter.MealClickListener {
                override fun onMealClick(meal: Meal) {
                    if (clickDebounce()) {
                        showMealDetails(meal)
                        Toast.makeText(
                            requireContext(),
                            "Тык на любую область блюда: ${meal.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFavoriteToggleClick(meal: Meal) {
                    viewModel.toggleFavorite(meal)
                    Toast.makeText(
                        requireContext(),
                        "Тык на сердечко: ${meal.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAddToCartClick(meal: Meal) {
                    //TODO("Not yet implemented")
                    Toast.makeText(
                        requireContext(),
                        "Добавляю в корзину 1 ${meal.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun plusToCartClick(meal: Meal) {
                    Toast.makeText(
                        requireContext(),
                        "Тык на плюсик: ${meal.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun minusToCartClick(meal: Meal) {
                    Toast.makeText(
                        requireContext(),
                        "Тык на минус: ${meal.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        binding.rvMenu.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = menuAdapter
        }
        menuAdapter.notifyDataSetChanged()
        return menuAdapter
    }


    private fun progressBarManager(status: VisibilityStatus) {
        binding.apply {
            when (status) {
                VisibilityStatus.VISIBLE -> {
                    searchProgressBar.isVisible = true
                    placeholderManager(VisibilityStatus.HIDDEN)
                }

                VisibilityStatus.HIDDEN -> {
                    searchProgressBar.isVisible = false
                }
            }
        }
    }

    private fun placeholderManager(status: VisibilityStatus) {
        binding.apply {
            when (status) {
                VisibilityStatus.VISIBLE -> {
                    tvPlaceholderMessage.isVisible = true
                    progressBarManager(VisibilityStatus.HIDDEN)
                }

                VisibilityStatus.HIDDEN -> {
                    tvPlaceholderMessage.isVisible = false
                }
            }
        }
    }

    private fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

    private companion object {
        enum class VisibilityStatus {
            VISIBLE,
            HIDDEN
        }

        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }
}