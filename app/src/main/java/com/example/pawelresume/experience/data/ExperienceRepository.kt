package com.example.pawelresume.experience.data

class ExperienceRepository constructor(private val experienceDao: ExperienceDao) {

    fun getAllExperience() = experienceDao.getExperience()

    suspend fun addExperience(entry: ExperienceEntry) = experienceDao.insertExperienceEntry(entry)
}