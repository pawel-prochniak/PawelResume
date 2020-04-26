package com.example.pawelresume.utils

import com.example.pawelresume.database.AppDatabase
import com.example.pawelresume.experience.data.ExperienceRepository
import com.example.pawelresume.experience.viewmodel.ExperienceListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object InjectorUtils {

    val myModule = module {
        single { AppDatabase.getInstance(get()).experienceDao() }
        single { ExperienceRepository(get()) }
        viewModel { ExperienceListViewModel(get()) }
    }

}