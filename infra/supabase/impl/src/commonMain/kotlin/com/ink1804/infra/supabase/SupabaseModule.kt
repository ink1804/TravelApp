package com.ink1804.infra.supabase

import com.ink1804.infra.supabase.SupabaseConst.webGoogleAuthClientId
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import org.koin.dsl.module

val supabaseModule = module {
    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = SupabaseConst.supabaseUrl,
            supabaseKey = SupabaseConst.supabaseKey,
        ) {
            install(Auth) {
                scheme = "travelapp"
                host = "auth-callback"
                alwaysAutoRefresh = false // default: true
                autoLoadFromStorage = false // default: true
            }
            install(ComposeAuth) {
                googleNativeLogin(webGoogleAuthClientId)
            }
            install(Postgrest)
        }
    }

    single<AuthDataSource> { SuperbaseAuthRepositoryImpl(get<SupabaseClient>().auth) }
    single<UserRemoteDataSource> {
        val client = get<SupabaseClient>()
        UserRemoteDataSourceImpl(
            auth = client.auth,
            users = get<SupabaseClient>().postgrest["users"],
        )
    }
}
