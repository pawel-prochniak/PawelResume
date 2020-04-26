package com.example.pawelresume.experience.data

fun ExperienceInput.toEntry() =
    ExperienceEntry(
        position = this.position,
        company = this.company,
        from = this.from,
        to = this.to
    )