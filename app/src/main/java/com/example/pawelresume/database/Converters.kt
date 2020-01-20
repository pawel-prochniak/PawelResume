package com.example.pawelresume.database

import androidx.room.TypeConverter
import java.sql.Date

class Converters {
    @TypeConverter
    fun dateToDatestamp(date: Date): Long = date.time

    @TypeConverter
    fun datestampToDate(datestamp: Long): Date = Date(datestamp)
}