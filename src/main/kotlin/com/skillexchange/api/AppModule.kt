package com.skillexchange.api

import com.skillexchange.api.services.AuthService
import org.koin.dsl.module

val appModule = module {
    single { AuthService() }
}
