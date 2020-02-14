package com.example.pawelresume.experience.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pawelresume.experience.data.ExperienceEntry
import com.example.pawelresume.experience.data.ExperienceRepository

class ExperienceListViewModel internal constructor(
    experienceRepository: ExperienceRepository
) : ViewModel() {
    val experienceList: LiveData<List<ExperienceEntry>> = experienceRepository.getAllExperience()
}