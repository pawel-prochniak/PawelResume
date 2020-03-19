package com.example.pawelresume.experience.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.pawelresume.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.time.Instant
import java.util.*

class ExperienceRepositoryTest {

    private lateinit var repo: ExperienceRepository
    private val expLiveData = MutableLiveData<List<ExperienceEntry>>()
    private val dao: ExperienceDao = mockk {
        every { getExperience() } returns expLiveData
    }


    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repo = ExperienceRepository.getInstance(dao)
    }

    @Test
    fun `GIVEN no instance created WHEN repository is called THEN an instance is returned`() {
        assertNotNull(repo)
    }

    @Test
    fun `GIVEN repo exists WHEN repository is called THEN the same instance is returned`() {
        val dao = mockk<ExperienceDao>()

        val repo2 = ExperienceRepository.getInstance(dao)

        assertEquals(repo, repo2)
    }

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
        expLiveData.postValue(experienceList)
        repo.getAllExperience().getOrAwaitValue().also { list ->
            assertEquals(experienceList, list)
        }

        expLiveData.postValue(experienceList)
    }

    interface StringFetcher {
        fun getAll(): LiveData<List<String>>
    }

    class StringRepository(private val fetcher: StringFetcher) {
        fun getStrings() = fetcher.getAll()
    }

    @Test
    @Ignore
    fun `string livedata test`() {
        val list = listOf("a", "b")
        val fetcher = mockk<StringFetcher>()
        val liveData = MutableLiveData<List<String>>()
        every { fetcher.getAll() } returns liveData

        val repo = StringRepository(fetcher)
        liveData.postValue(list)

        assertEquals(list, repo.getStrings().value)
    }
}