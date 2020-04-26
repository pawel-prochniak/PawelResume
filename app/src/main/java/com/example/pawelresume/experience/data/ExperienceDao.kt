package com.example.pawelresume.experience.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExperienceDao {
    @Query("SELECT * FROM experience ORDER BY from_date")
    fun getExperience(): LiveData<List<ExperienceEntry>>

    @Insert
    suspend fun insertExperienceEntry(entry: ExperienceEntry): Long

}