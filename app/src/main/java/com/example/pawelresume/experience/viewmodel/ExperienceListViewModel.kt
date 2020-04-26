package com.example.pawelresume.experience.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pawelresume.experience.data.ExperienceEntry
import com.example.pawelresume.experience.data.ExperienceInput
import com.example.pawelresume.experience.data.ExperienceRepository
import com.example.pawelresume.experience.data.toEntry
import kotlinx.coroutines.launch

class ExperienceListViewModel internal constructor(
    private val experienceRepository: ExperienceRepository
) : ViewModel() {
    val experienceList: LiveData<List<ExperienceEntry>> = experienceRepository.getAllExperience()

    fun addExperience(data: ExperienceInput) {
        viewModelScope.launch {
            experienceRepository.addExperience(data.toEntry())
        }
    }
}