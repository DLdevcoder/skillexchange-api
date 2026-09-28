package com.skillexchange.api

import com.skillexchange.api.config.DatabaseConfig
import com.skillexchange.api.plugins.configureAuth
import com.skillexchange.api.plugins.configureCORS
import com.skillexchange.api.plugins.configureMonitoring
import com.skillexchange.api.plugins.configureRouting
import com.skillexchange.api.plugins.configureSerialization
import com.skillexchange.api.plugins.configureStatusPages
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port = port, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    // Koin DI
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }

    // Plugins (order matters)
    configureSerialization()
    configureCORS()
    configureMonitoring()
    configureStatusPages()
    configureAuth()
    configureRouting()

    // Database
    DatabaseConfig.init()
}
