package xyz.schwaab.example

import com.russhwolf.settings.Settings
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

object TestingServiceRegistry {

    internal fun appStart(settings: Settings, postRepository: PostRepositoryFake){
        val coreModule = module {
            single { settings }
            single { postRepository }
        }

        startKoin { modules(coreModule) }

    }

    internal fun appEnd(){
        stopKoin()
    }
}