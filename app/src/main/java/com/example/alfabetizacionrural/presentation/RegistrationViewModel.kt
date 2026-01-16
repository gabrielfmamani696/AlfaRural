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

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "alfabetizacion-db"
    ).build()

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState = _uiState.asStateFlow()

    fun onAliasChange(newAlias: String) {
        _uiState.value = _uiState.value.copy(alias = newAlias)
    }

    fun onAvatarSelected(avatarId: Int) {
        _uiState.value = _uiState.value.copy(selectedAvatarId = avatarId)
    }

    fun registerUser(onSuccess: () -> Unit) {
        val currentState = _uiState.value
        if (currentState.alias.isBlank() || currentState.selectedAvatarId == null) return

        viewModelScope.launch {
            val newUser = UserEntity(
                alias = currentState.alias,
                avatarId = currentState.selectedAvatarId!!
                // Los demás campos se llenan solos con sus valores por defecto
            )
            db.userDao().insertUser(newUser)
            onSuccess()
        }
    }
}

data class RegistrationUiState(
    val alias: String = "",
    val selectedAvatarId: Int? = null
)