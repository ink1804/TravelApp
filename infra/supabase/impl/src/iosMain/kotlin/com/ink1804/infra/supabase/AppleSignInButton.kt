package com.ink1804.infra.supabase

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ink1804.core.logger.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import org.koin.compose.koinInject

@Composable
internal fun AppleSignInButton() {
    val supabaseClient: SupabaseClient = koinInject()
    //todo change to apple auth

    val authState = supabaseClient.composeAuth.rememberSignInWithGoogle(
        onResult = { Log.d("Auth", "Result: $it") }
    )

    Button(onClick = { authState.startFlow() }) {
        Text("Sign in with Google")
    }
}
