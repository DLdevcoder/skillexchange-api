package com.skillexchange.api.plugins

import com.skillexchange.api.routes.authRoutes
import com.skillexchange.api.routes.healthRoutes
import com.skillexchange.api.services.AuthService
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    // Inject services ở Application level — tương thích hoàn toàn với Koin 4.x + Ktor 3.x
    val authService: AuthService by inject()

    routing {
        healthRoutes()
        authRoutes(authService)
    }
}
