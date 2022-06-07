package com.example.atnm.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pairs(
    val from: String,
    val to: String
) : Parcelable
