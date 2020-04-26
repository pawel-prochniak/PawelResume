package com.example.pawelresume.experience.data

import androidx.room.ColumnInfo
import java.util.*

data class ExperienceInput (
    val position: String,
    val company: String,
    val from: Date,
    val to: Date = Calendar.getInstance().time
)