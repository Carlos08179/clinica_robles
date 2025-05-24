package com.example.clinicaroblesapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Specialty(
    val name: String,
    val doctorName: String,
    val schedule: String,
    val iconResId: Int
) : Parcelable