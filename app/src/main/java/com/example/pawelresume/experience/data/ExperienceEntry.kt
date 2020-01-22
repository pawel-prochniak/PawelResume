package com.example.pawelresume.experience.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pawelresume.database.Converters
import java.util.*

@Entity(tableName = "experience")
@TypeConverters(Converters::class)
data class ExperienceEntry(
    @ColumnInfo(name = "position") val position: String,
    @ColumnInfo(name = "company") val company: String,
    @ColumnInfo(name = "from_date")  val from: Date,
    @ColumnInfo(name = "to_date") val to: Date = Calendar.getInstance().time
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var entryId: Long = 0
}