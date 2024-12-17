package com.mandarinkafe.mandarin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandarinkafe.mandarin.menu.data.dto.ItemCategory
import com.mandarinkafe.mandarin.menu.domain.api.MenuInteractor
import kotlinx.coroutines.launch

class MainViewModel(private val menuInteractor: MenuInteractor) : ViewModel() {

    private var screenState = MutableLiveData<ScreenState>(ScreenState.Loading)
    fun getScreenState(): LiveData<ScreenState> = screenState

    fun getMenu() {
        screenState.postValue(ScreenState.Loading)
        viewModelScope.launch {

            menuInteractor.getMenu().collect { pair ->
                processMenuResult(pair.first, pair.second)
            }
        }
    }

    private fun processMenuResult(menu: ArrayList<ItemCategory>?, errorMessage: String?) {

        if (!menu.isNullOrEmpty()) {
            screenState.postValue(ScreenState.Content(menu))
        }
        if (errorMessage != null) {
            screenState.postValue(ScreenState.Error(errorMessage))
        }
    }


    sealed interface ScreenState {
        data object Loading : ScreenState
        data class Error(var errorMessage: String) : ScreenState
        data class Content(var menu: ArrayList<ItemCategory>) : ScreenState
    }

}