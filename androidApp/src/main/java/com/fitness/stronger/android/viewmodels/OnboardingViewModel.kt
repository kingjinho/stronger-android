package com.fitness.stronger.android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OnboardingViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            OnboardingViewModel() as T
        } else {
            throw IllegalArgumentException("cannot assign viewmodel as onbonarding viewmodel!")
        }
    }
}

class OnboardingViewModel(

) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}