package com.ink1804.infra.supabase

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ink1804.core.logger.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import org.koin.compose.koinInject

@Composable
internal fun GoogleSignInButton() {
    val supabaseClient: SupabaseClient = koinInject()

    val authState = supabaseClient.composeAuth.rememberSignInWithGoogle(
        onResult = {
            when (it) {
                NativeSignInResult.ClosedByUser -> Log.d("Auth", "ClosedByUser")
                is NativeSignInResult.Error -> Log.d("Auth", "Error: ${it.exception}")
                is NativeSignInResult.NetworkError -> Log.d("Auth", "NetworkError ${it.message}")
                NativeSignInResult.Success -> Log.d("Auth", "Success")
            }
        }
    )
    Button(onClick = { authState.startFlow() }) {
        Text("Sign in with Google")
    }
}
