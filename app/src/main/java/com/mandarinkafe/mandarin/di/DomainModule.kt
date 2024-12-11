package com.mandarinkafe.mandarin.di

import com.mandarinkafe.mandarin.menu.domain.api.FavoritesInteractor
import com.mandarinkafe.mandarin.menu.domain.impl.FavoritesInteractorImpl
import org.koin.dsl.module

val domainModule = module {

    single<FavoritesInteractor> {
        FavoritesInteractorImpl(repository = get())
    }
}