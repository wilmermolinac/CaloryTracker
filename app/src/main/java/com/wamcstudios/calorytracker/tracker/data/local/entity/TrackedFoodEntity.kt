package com.wamcstudios.calorytracker.tracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Month

@Entity()
data class TrackedFoodEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int, val calories: Int,
    val imageUrl: String?,
    val type: String,
    val amount: Int,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
)
