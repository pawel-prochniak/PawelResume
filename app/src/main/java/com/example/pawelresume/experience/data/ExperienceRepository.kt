package com.example.pawelresume.experience.data

class ExperienceRepository private constructor(private val experienceDao: ExperienceDao) {

    fun getAllExperience() = experienceDao.getExperience()

    suspend fun addExperience(entry: ExperienceEntry) = experienceDao.insertExperienceEntry(entry)

    companion object {
        @Volatile private var instance: ExperienceRepository? = null

        fun getInstance(experienceDao: ExperienceDao) =
            instance ?: synchronized(this) {
                instance ?: ExperienceRepository(experienceDao).also { instance = it }
            }
    }
}