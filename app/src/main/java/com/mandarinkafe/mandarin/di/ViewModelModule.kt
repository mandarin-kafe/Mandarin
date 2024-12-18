package com.mandarinkafe.mandarin.di

import com.mandarinkafe.mandarin.MockMenuViewModel
import com.mandarinkafe.mandarin.SharedViewModel
import com.mandarinkafe.mandarin.meal_details.ui.MealDetailsViewModel
import com.mandarinkafe.mandarin.menu.domain.models.Item
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<MealDetailsViewModel> { (item: Item) ->
        MealDetailsViewModel(item = item, favoritesInteractor = get())
    }

    viewModel<SharedViewModel> {
        SharedViewModel(menuInteractor = get(), favoritesInteractor = get() )
    }

    //TODO удалить, когда будет налажена интеграция меню
    viewModel<MockMenuViewModel> {
        MockMenuViewModel(menuInteractor = get(), favoritesInteractor = get() )
    }

}