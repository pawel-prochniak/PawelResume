package com.example.pawelresume.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time

    @TypeConverter
    fun timestampToDate(timestamp: Long): Date = Date(timestamp)
}