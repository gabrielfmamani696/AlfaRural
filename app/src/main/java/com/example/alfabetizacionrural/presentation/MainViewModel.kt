package com.example.alfabetizacionrural.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.alfabetizacionrural.data.local.AppDatabase
import com.example.alfabetizacionrural.data.local.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
/*
Proposito
 */
/*
application representa la app, se usa en ViewModel para realizar operaciones que necesitan permiso
*/

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "alfabetizacion-db"
    ).build()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            _currentUser.value = db.userDao().getCurrentUser()
            _isLoading.value = false
        }
    }
}
