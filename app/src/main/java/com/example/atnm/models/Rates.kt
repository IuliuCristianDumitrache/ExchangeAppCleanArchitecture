package com.example.atnm.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rates(
    val from: String,
    val to: String,
    val rate: Double
): Parcelable