package xyz.schwaab.example

import com.russhwolf.settings.MockSettings
import xyz.schwaab.example.ktor.PostRepository
import xyz.schwaab.example.models.Community
import xyz.schwaab.example.models.Post
import xyz.schwaab.example.models.PostModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class PostModelTest {

    private lateinit var model: PostModel
    private val settings = MockSettings()
    private val repository = PostRepositoryFake()

    @BeforeTest
    fun setup() {
        TestingServiceRegistry.appStart(settings, repository)
        model = PostModel(repository) { _ ->

        }
    }


    @AfterTest
    fun breakdown() {
        TestingServiceRegistry.appEnd()
    }
}

class PostRepositoryFake : PostRepository {
    override suspend fun fetchPosts(community: Community): List<Post> {
        return when (community) {
            SUPPORTED_COMMUNITY1 -> listOf()
            SUPPORTED_COMMUNITY2 -> listOf()
            else -> TODO("Not supported")
        }
    }

    companion object {
        val SUPPORTED_COMMUNITY1 = Community("Kotlin")
        val SUPPORTED_COMMUNITY2 = Community("ProgrammerHumor")
    }
}