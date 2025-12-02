package com.app.inventario.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.inventario.data.local.datastore.UserDataStore
import com.app.inventario.data.remote.dto.auth.AuthLoginResponseDto
import com.app.inventario.data.repository.AuthRepository
import com.app.inventario.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userDataStore: UserDataStore
): ViewModel() {

    // Variables



    // Estado UI
    private val _loginState = MutableStateFlow<UiState<AuthLoginResponseDto>>(UiState.IdLe)
    val loginState: StateFlow<UiState<AuthLoginResponseDto>> = _loginState


    // Metodos Privados



    // Metodos Publicos

    fun login(username: String, password: String) {
        viewModelScope.launch {
            authRepository.login(username, password)
                .onStart {
                    _loginState.value = UiState.Loading
                }
                .catch { e ->
                    _loginState.value = UiState.Error(e.message ?: "Error desconocido")
                }
                .collect { result ->
                    result.onSuccess { data ->
                        userDataStore.saveUserData(authResponse = data)
                        _loginState.value = UiState.Success(data)
                    }
                    result.onFailure { e ->
                        _loginState.value = UiState.Error(e.message ?: "Error desconocido")
                    }
                }
        }
    }

}
