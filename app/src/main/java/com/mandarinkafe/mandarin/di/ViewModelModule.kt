package com.mandarinkafe.mandarin.di

import com.mandarinkafe.mandarin.meal_details.ui.MealDetailsViewModel
import com.mandarinkafe.mandarin.menu.domain.models.Meal
import com.mandarinkafe.mandarin.menu.ui.MenuViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<MealDetailsViewModel> { (meal: Meal) ->
        MealDetailsViewModel(meal = meal, favoritesInteractor = get())
    }

    viewModel<MenuViewModel> {
        MenuViewModel(favoritesInteractor = get())
    }
}