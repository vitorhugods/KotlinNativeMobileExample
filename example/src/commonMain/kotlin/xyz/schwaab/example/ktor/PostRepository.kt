package xyz.schwaab.example.ktor

import xyz.schwaab.example.models.Community
import xyz.schwaab.example.models.Post

interface PostRepository {
    suspend fun fetchPosts(community: Community): List<Post>
}