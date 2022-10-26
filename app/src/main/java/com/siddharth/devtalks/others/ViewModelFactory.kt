package com.siddharth.devtalks.others

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.siddharth.devtalks.auth.AuthViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory(), KoinComponent {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> get<AuthViewModel>() as T
            else -> throw RuntimeException("Unable to create instance of ${modelClass.simpleName}. Did you forget to update the ViewModelFactory?")
        }
    }
}