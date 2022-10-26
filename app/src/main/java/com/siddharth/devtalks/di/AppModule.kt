package com.siddharth.devtalks.di

import com.siddharth.devtalks.auth.AuthViewModel
import com.siddharth.devtalks.others.ViewModelFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single { ViewModelFactory() }
    viewModel { AuthViewModel() }
}