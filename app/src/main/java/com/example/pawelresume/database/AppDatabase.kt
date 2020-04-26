package com.example.pawelresume.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pawelresume.experience.data.ExperienceDao
import com.example.pawelresume.experience.data.ExperienceEntry
import com.example.pawelresume.utils.DATABASE_NAME
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.sql.Date
import java.util.*
import kotlin.coroutines.coroutineContext

@Database(entities = [ExperienceEntry::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun experienceDao(): ExperienceDao

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME)
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                            getInstance(context).experienceDao().apply {
                                PREPOPULATE_DATA.forEach {
                                    GlobalScope.launch {
                                    insertExperienceEntry(it)
                                }
                            }
                        }
                    }
                })
                .build()

        val PREPOPULATE_DATA = arrayListOf(
            ExperienceEntry(
                "Android Team Leader",
                "Ailleron",
                from = Calendar.getInstance().apply { set(2019, 1, 1) }.time
            ),
            ExperienceEntry(
                "Android Developer",
                "Ailleron",
                from = Calendar.getInstance().apply { set(2018, 2, 1) }.time,
                to = Calendar.getInstance().apply { set(2019, 1, 1) }.time
            ),
            ExperienceEntry(
                "Android Developer",
                "SoInteractive",
                from = Calendar.getInstance().apply { set(2016, 9, 1) }.time,
                to = Calendar.getInstance().apply { set(2018, 1, 31) }.time
            ),
            ExperienceEntry(
                "Junior Android Developer",
                "SoInteractive",
                from = Calendar.getInstance().apply { set(2015, 9, 1) }.time,
                to = Calendar.getInstance().apply { set(2016, 9, 30) }.time
            ),
            ExperienceEntry(
                "Android Developer Intern",
                "SoInteractive",
                from = Calendar.getInstance().apply { set(2015, 7, 1) }.time,
                to = Calendar.getInstance().apply { set(2015, 8, 30) }.time
            )
        )

    }
}