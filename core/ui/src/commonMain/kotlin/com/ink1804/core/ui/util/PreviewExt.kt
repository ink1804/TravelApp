package com.ink1804.core.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Composable
fun <T> T.toPreviewState(): State<T> = remember { derivedStateOf { this } }
