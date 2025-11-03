package com.ink1804.app

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel<MainActivityViewModel> { MainActivityViewModel(get()) }
}
