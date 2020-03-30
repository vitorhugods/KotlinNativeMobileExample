package xyz.schwaab.example.models

import co.touchlab.stately.ensureNeverFrozen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import xyz.schwaab.example.ktor.PostRepository

@ExperimentalCoroutinesApi
class PostModel(private val repository: PostRepository,
                private val viewUpdate: (List<Post>) -> Unit) : BaseModel() {
    init {
        ensureNeverFrozen()
    }

    fun fetchPosts(communityName: String) {
        ktorScope.launch {
            val posts = repository.fetchPosts(Community(communityName))

            mainScope.launch {
                viewUpdate(posts)
            }
        }
    }
}
