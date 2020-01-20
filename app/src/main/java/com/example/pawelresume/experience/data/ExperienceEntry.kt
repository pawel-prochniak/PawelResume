package com.example.pawelresume.experience.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "experience")
data class ExperienceEntry(
    @ColumnInfo(name = "position") val position: String,
    @ColumnInfo(name = "company") val company: String,
    @ColumnInfo(name = "from_date") val from: Date,
    @ColumnInfo(name = "to_date") val to: Date = Calendar.getInstance().time
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val entryId: Long = 0
}