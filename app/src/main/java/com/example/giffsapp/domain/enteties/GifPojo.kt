package com.example.giffsapp.domain.enteties

import com.google.gson.annotations.SerializedName

data class Gif(
    val data: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)

data class Meta(
    val msg: String,
    val response_id: String,
    val status: Number
)

data class Pagination(
    val count: Number,
    val offset: Number,
    val total_count: Number
)

data class Data(
    val analytics: Analytics,
    val analytics_response_payload: String,
    val bitly_gif_url: String,
    val bitly_url: String,
    val content_url: String,
    val embed_url: String,
    val id: String,
    val images: Images,
    val import_datetime: String,
    val is_sticker: Number,
    val rating: String,
    val slug: String,
    val source: String,
    val source_post_url: String,
    val source_tld: String,
    val title: String,
    val trending_datetime: String,
    val type: String,
    val url: String,
    val user: User,
    val username: String
)

data class Analytics(
    val onclick: Looping,
    val onload: Looping,
    val onsent: Looping
)

data class Images(
    @SerializedName(value = "480w_still")
    val _480w_still: Looping,
    val downsized: Looping,
    val downsized_large: Looping,
    val downsized_medium: Looping,
    val downsized_small: Looping,
    val downsized_still: Looping,
    val fixed_height: Looping,
    val fixed_height_downsampled: Looping,
    val fixed_height_small: Looping,
    val fixed_height_small_still: Looping,
    val fixed_height_still: Looping,
    val fixed_width: Looping,
    val fixed_width_downsampled: Looping,
    val fixed_width_small: Looping,
    val fixed_width_small_still: Looping,
    val fixed_width_still: Looping,
    val looping: Looping,
    val original: Looping,
    val original_mp4: Looping,
    val original_still: Looping,
    val preview: Looping,
    val preview_gif: Looping,
    val preview_webp: Looping
)

data class User(
    val avatar_url: String,
    val banner_image: String,
    val banner_url: String,
    val description: String,
    val display_name: String,
    val instagram_url: String,
    val is_verified: Boolean,
    val profile_url: String,
    val username: String,
    val website_url: String
)

data class Looping(
    val frames: String?,
    val hash: String?,
    val height: String?,
    val mp4: String?,
    val mp4_size: String?,
    val size: String?,
    val url: String?,
    val webp: String?,
    val webp_size: String?,
    val width: String?
)
