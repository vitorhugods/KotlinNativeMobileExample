package xyz.schwaab.example.ktor.reddit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditPostResultDTO(
        @SerialName("data") val data: RedditDataDTO
)

@Serializable
data class RedditDataDTO(
        @SerialName("children") val children: List<RedditChildDTO>
)

@Serializable
data class RedditChildDTO(
        @SerialName("kind") val kind: String,
        @SerialName("data") val data: RedditPostDTO
) {
    val post: RedditPostDTO?
        get() {
            return if (kind == "t3")
                data
            else null
        }
}

@Serializable
data class RedditPostDTO(
        @SerialName("id") val id: String,
        @SerialName("url") val url: String,
        @SerialName("title") val title: String,
        @SerialName("author") val authorName: String
)
