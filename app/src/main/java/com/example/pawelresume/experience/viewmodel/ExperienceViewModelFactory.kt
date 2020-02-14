package com.example.pawelresume.experience.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pawelresume.experience.data.ExperienceRepository


class ExperienceViewModelFactory(
    private val experienceRepository: ExperienceRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExperienceListViewModel(experienceRepository) as T
    }
}