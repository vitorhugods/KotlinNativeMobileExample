package xyz.schwaab.example

actual fun currentTimeInMillis(): Long = System.currentTimeMillis()

internal actual fun printThrowable(t: Throwable) {
    t.printStackTrace()
}
