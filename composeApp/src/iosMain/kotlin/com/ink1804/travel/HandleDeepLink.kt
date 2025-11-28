package com.ink1804.travel

import com.ink1804.core.logger.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.handleDeeplinks
import org.koin.mp.KoinPlatform.getKoin
import platform.Foundation.NSURL

fun doHandleDeepLink(url: NSURL) {
    val client: SupabaseClient = getKoin().get()
    Log.d("Deeplink", "Deep link: $url")
    client.handleDeeplinks(url)
}