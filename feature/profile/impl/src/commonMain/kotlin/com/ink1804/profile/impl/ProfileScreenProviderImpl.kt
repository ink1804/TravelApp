package com.ink1804.profile.impl

import androidx.compose.runtime.Composable
import com.ink1804.profile.api.ProfileComponent
import com.ink1804.profile.api.ProfileScreenProvider

class ProfileScreenProviderImpl : ProfileScreenProvider {
    @Composable
    override fun Content(component: ProfileComponent) {
        ProfileScreen(component)
    }
}
