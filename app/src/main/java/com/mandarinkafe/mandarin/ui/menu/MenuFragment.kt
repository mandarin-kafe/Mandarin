package com.mandarinkafe.mandarin.ui.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.FragmentMenuBinding
import com.mandarinkafe.mandarin.domain.models.Meal
import com.mandarinkafe.mandarin.domain.models.MenuCategory
import com.mandarinkafe.mandarin.domain.models.MenuItem
import com.mandarinkafe.mandarin.ui.meal_details.MealDetailsFragment
import com.mandarinkafe.mandarin.ui.menu.MenuViewModel.MenuScreenState

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val tabLayoutMain: TabLayout by lazy { binding.tabLayoutCategories }
    private val tabLayoutSub: TabLayout by lazy { binding.tabLayoutSubCategories }
    private val recyclerView: RecyclerView by lazy { binding.rvMenu }
    private var isClickAllowed = true
    private var isTabSyncing = false
    private val handler = Handler(Looper.getMainLooper())
    private val viewModel: MenuViewModel by viewModels()

    private var menuSubCategoriesPizza = arrayListOf<String>(
        "Классическая",
        "Римская",
        "Неаполитано",
        "Чикаго",
        "Кальцоне",
        "Фокачча"
    )
    private var menuSubCategoriesSushi = arrayListOf<String>(
        "Роллы",
        "Маки-суши",
        "Нигири",
        "Гунканы",
        "Спайси",
        "Запеченные роллы",
        "Горячие роллы", "Онигири", "Сеты"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.prepareMenuItems()
        viewModel.getMenuState().observe(viewLifecycleOwner)

        { state -> renderMenuScreen(state) }

    }

    override fun onResume() {
        super.onResume()
        Log.d(
            "DEBUG",
            "Метод onResume, tabLayoutMain.visibility = ${tabLayoutMain.isVisible.toString()}"
        )

        for (i in 0 until tabLayoutMain.tabCount) {
            val tab = tabLayoutMain.getTabAt(i)
            Log.d("TabLayout", "Вкладка $i: ${tab?.text ?: "Без текста"}")
            tabLayoutMain.visibility = View.VISIBLE
            tabLayoutSub.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabLayoutMain.removeAllTabs()
        Log.d("DEBUG", "Метод onDestroyView, удаляю все вкладки у tabLayoutMain")
    }


    private fun setTabs(menuCategories: ArrayList<MenuCategory>) {
        menuCategories.forEach { category ->
            val tab = tabLayoutMain.newTab().setText(category.name)
            when (category.name.lowercase()) {
                "пицца" -> tab.setIcon(R.drawable.pizza)
                "суши и роллы" -> tab.setIcon(R.drawable.sushi)
                "бургеры" -> tab.setIcon(R.drawable.burger)
                "хот-доги и донер" -> tab.setIcon(R.drawable.hotdog)
                "wok" -> tab.setIcon(R.drawable.wok)
            }
            tabLayoutMain.addTab(tab)
            Log.d("DEBUG", "Метод setTabs, добавляю вкладку ${tab.text}")
        }
    }


    private fun addOnTabSelectedListener(
        menuCategories: ArrayList<MenuCategory>,
        menuItems: MutableList<MenuItem>
    ) {
        Log.d(
            "DEBUG",
            "Вызов метода addOnTabSelectedListener для усановки OnTabSelectedListener"
        )
        tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    Log.d(
                        "DEBUG",
                        "Вызов onTabSelected для вкладки ${tab.text.toString()}, значение isTabSyncing = $isTabSyncing"
                    )
                    subTabsManager(tab.text.toString())
                }
                if (isTabSyncing) return
                val position = tab?.position ?: 0
                if (tab != null) {
                    scrollToCategory(position, menuCategories, menuItems)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("DEBUG", "Очищаю дочерние вкладки в методе onTabUnselected")
                tabLayoutSub.removeAllTabs()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setScrollSync(
        menuCategories: ArrayList<MenuCategory>,
        menuItems: MutableList<MenuItem>
    ) {
        Log.d("DEBUG", "Вызвала метод setScrollSync для установки addOnScrollListener")
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (isTabSyncing) return // Игнорируем вызовы, если синхронизация уже идёт
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                val firstVisiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()

                if (firstVisiblePosition != RecyclerView.NO_POSITION) {
                    for (position in firstVisiblePosition..lastVisiblePosition) {
                        val item = menuItems[position]
                        val categoryName = when (item) {
                            is MenuItem.Header -> item.categoryName
                            is MenuItem.MealItem -> {
                                menuCategories.find { category -> category.items.contains(item.meal) }?.name
                            }

                            else -> null
                        }

                        if (categoryName != null) {
                            val headerIndex =
                                menuCategories.indexOfFirst { it.name == categoryName }
                            if (headerIndex != -1 && headerIndex != tabLayoutMain.selectedTabPosition) {
                                isTabSyncing = true

                                val tab = tabLayoutMain.getTabAt(headerIndex)
                                tab?.select()
                                isTabSyncing = false
                            }
                            break
                        }
                    }
                }
            }
        })
    }

    private fun subTabsManager(category: String) {
        Log.d("DEBUG", "Вызов  subTabsManager для $category")
        //временный метод для проверки вёрстки. Нужно будет сделать нормальный,
        // когда поймём структуру их фида и как вытащить субкатегории и родительскую категорию.

        when ((category.lowercase())) {
            "pizza", "пицца" -> {
                Log.d("DEBUG", "вызываю setSubTabs для пиццы")
                setSubTabs(menuSubCategoriesPizza)
            }

            "sushi", "суши и роллы" -> {
                Log.d("DEBUG", "вызываю setSubTabs для суши")
                setSubTabs(menuSubCategoriesSushi)
            }

            else -> tabLayoutSub.isVisible = false
        }
    }

    private fun setSubTabs(subCategories: ArrayList<String>) {
        tabLayoutSub.removeAllTabs()

        if (subCategories.isEmpty()) {
            Log.e("DEBUG", "Список подкатегорий пуст!")
            return
        }
        subCategories.forEach { subCategory ->
            tabLayoutSub.addTab(tabLayoutSub.newTab().setText(subCategory))
        }
        tabLayoutSub.isVisible = subCategories.isNotEmpty()

        // Проверяем слушатели, чтобы они не добавлялись повторно
        if (tabLayoutSub.tabCount > 0 && tabLayoutSub.getTabAt(0)?.tag == null) {
            tabLayoutSub.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // TODO Логика выбора подкатегории
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun scrollToCategory(
        position: Int, menuCategories: ArrayList<MenuCategory>,
        menuItems: MutableList<MenuItem>
    ) {
        val headerPosition = menuItems.indexOfFirst {
            it is MenuItem.Header && it.categoryName == menuCategories[position].name
        }
        if (headerPosition != -1) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val offset = resources.getDimensionPixelSize(R.dimen.recycler_view_offset_1)
            layoutManager.scrollToPositionWithOffset(headerPosition, offset)
        }

    }

    private fun showMealDetails(meal: Meal) {
        findNavController().navigate(
            R.id.action_menuFragment_to_mealDetails,
            MealDetailsFragment.createArgs(meal)
        )
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
                setTabs(state.menuCategories)
                setScrollSync(state.menuCategories, state.menuItems)
                addOnTabSelectedListener(state.menuCategories, state.menuItems)
                setRvAdapter(state.menuItems)

                val defaultTab = tabLayoutMain.getTabAt(0)
                if (defaultTab != null) {
                    subTabsManager(defaultTab.text.toString())
                    Log.d(
                        "DEBUG",
                        "Установила дефолтную вкладку, вызываю subTabsManager для вкладки ${defaultTab.text.toString()}"
                    )
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRvAdapter(menuItems: MutableList<MenuItem>): MenuAdapter {
        val menuAdapter = MenuAdapter(menuItems,
            object : MenuAdapter.MealClickListener {
                override fun onMealClick(meal: Meal) {
                    if (clickDebounce()) {
                        if (meal.category == "pizza") showMealDetails(meal)
                        Toast.makeText(
                            requireContext(),
                            "Тык на любую область блюда: ${meal.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onEditClick(meal: Meal) {
                    showMealDetails(meal)
                    Toast.makeText(
                        requireContext(),
                        "Тык на кнопку редактирования: ${meal.name}",
                        Toast.LENGTH_SHORT
                    ).show()
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
//            when (status) {
//                VisibilityStatus.VISIBLE -> {
//                    searchProgressBar.isVisible = true
//                    placeholderManager(VisibilityStatus.HIDDEN)
//                }
//
//                VisibilityStatus.HIDDEN -> {
//                    searchProgressBar.isVisible = false
//                }
//            }
        }
    }

    private fun placeholderManager(status: VisibilityStatus) {
        binding.apply {
//            when (status) {
//                VisibilityStatus.VISIBLE -> {
//                    tvPlaceholderMessage.isVisible = true
//                    progressBarManager(VisibilityStatus.HIDDEN)
//                }
//
//                VisibilityStatus.HIDDEN -> {
//                    tvPlaceholderMessage.isVisible = false
//                }
//            }
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