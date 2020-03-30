package xyz.schwaab.example

expect fun currentTimeInMillis(): Long

internal expect fun printThrowable(t: Throwable)