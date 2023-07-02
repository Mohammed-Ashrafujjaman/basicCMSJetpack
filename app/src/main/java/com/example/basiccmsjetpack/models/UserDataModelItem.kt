package com.example.basiccmsjetpack.models

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Locale

@JsonClass(generateAdapter = true)
data class UserDataModelItem(
    @Json(name = "email")
    val email: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String,
    @Json(name = "status")
    val status: String
) {
    fun getStatusLabel(): String {
        return status.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }
    fun getGenderLabel(): String {
        return gender.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }

    @ColorRes
    fun getStatusColor(): Color {
        return if (status.lowercase() == "active") {
            Color(0xFF16a085)
        } else {
            Color(0xFFc0392b)
        }
    }
    fun getAvatarImageUrl() = "https://picsum.photos/seed/u$id/200/200"
}
