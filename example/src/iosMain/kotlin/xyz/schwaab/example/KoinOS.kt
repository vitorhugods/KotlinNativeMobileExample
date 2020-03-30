package xyz.schwaab.example

import com.russhwolf.settings.AppleSettings
import org.koin.dsl.module

fun initKoin() = initKoin {}
actual val platformModule = module {
    single { AppleSettings.Factory().create("EXAMPLE_SETTINGS") }
}