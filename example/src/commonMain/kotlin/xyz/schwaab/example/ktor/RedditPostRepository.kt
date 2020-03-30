package xyz.schwaab.example.ktor

import xyz.schwaab.example.models.Post
import co.touchlab.stately.ensureNeverFrozen
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.http.takeFrom
import xyz.schwaab.example.ktor.reddit.*
import xyz.schwaab.example.models.Community

class RedditPostRepository : PostRepository {
    private val client = HttpClient {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    init {
        ensureNeverFrozen()
    }

    override suspend fun fetchPosts(community: Community): List<Post> {
        //TODO treat errors and all
        return client.post<RedditPostResultDTO> {
            fetchCommunityPosts(community)
        }.data.children.mapNotNull {
            it.post?.toModel()
        }
    }

    private fun RedditPostDTO.toModel() = Post(id, url, title, authorName)

    private fun HttpRequestBuilder.fetchCommunityPosts(community: Community) {
        url {
            takeFrom("https://reddit.com")
            encodedPath = "r/${community.name}.json"
        }
        build()
    }
}
