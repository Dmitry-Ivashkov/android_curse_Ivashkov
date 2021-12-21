package com.example.android_curse.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Post(
    @Json(name = "id") val id: Long,
    @Json(name = "link") val linkUrl: String?,
    @Json(name = "image") val imageUrl: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "text") val text: String?,
    @Json(name = "created_at") val createAt: String,
    @Json(name = "updated_at") val updateAt: String,
    @Json(name = "likes") val likes: Lakes,
)

data class Lakes (
    @Json(name = "first_users") val firstUsers: List<User>,
    @Json(name = "total_count") val totalCount: Int
)
