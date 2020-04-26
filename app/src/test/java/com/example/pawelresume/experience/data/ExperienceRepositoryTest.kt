package com.example.pawelresume.experience.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.pawelresume.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.*

class ExperienceRepositoryTest {

    private val expLiveData = MutableLiveData<List<ExperienceEntry>>()
    private val dao: ExperienceDao = mockk()
    private val repo = ExperienceRepository(dao)

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val experienceList = listOf(
        ExperienceEntry(
            "pos1",
            "comp1",
            from = Calendar.getInstance().apply { set(2018, 2, 1) }.time,
            to = Calendar.getInstance().apply { set(2019, 1, 1) }.time
        ),
        ExperienceEntry(
            "pos2",
            "comp2",
            from = Calendar.getInstance().apply { set(2016, 2, 1) }.time,
            to = Calendar.getInstance().apply { set(2018, 1, 1) }.time)
    )
    
    @Test
    fun `GIVEN dao returns experience list WHEN all experience is requested THEN list is returned`() {
        every { dao.getExperience() } returns expLiveData

        expLiveData.postValue(experienceList)

        repo.getAllExperience().getOrAwaitValue().also {
            assertEquals(experienceList, it)
        }

    }

    @Test
    fun `GIVEN dao accepts entry WHEN experience is added THEN its id is returned`() {
        val id = 1L
        every { dao.insertExperienceEntry(any()) } returns id

        val entry = ExperienceEntry(
            "Dev",
            "ITM",
            from = Calendar.getInstance().apply { set(2020, 2, 1)}.time)
        val returned = repo.addExperience(entry)
        assertEquals(id, returned)
    }
}