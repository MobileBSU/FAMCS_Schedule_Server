package com.example.di

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.log
import org.koin.ktor.plugin.Koin


fun Application.configureDI() {
    try {
        install(Koin) {
            modules(appModule)
        }
    } catch (e: Exception) {
        log.error("Koin initialization failed: ${e.message}")
    }
}