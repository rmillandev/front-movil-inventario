package com.app.inventario.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.inventario.data.local.datastore.UserDataStore
import com.app.inventario.data.remote.dto.auth.AuthLoginDto
import com.app.inventario.data.remote.dto.auth.AuthLoginResponseDto
import com.app.inventario.data.repository.AuthRepository
import com.app.inventario.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userDataStore: UserDataStore
): ViewModel() {

    // Variables
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState



    // Estado UI
    private val _loginResult = MutableStateFlow<UiState<AuthLoginResponseDto>>(UiState.IdLe)
    val loginResult: StateFlow<UiState<AuthLoginResponseDto>> = _loginResult

    private val _loginEvents = MutableSharedFlow<LoginEvent>()
    val loginEvents = _loginEvents.asSharedFlow()


    fun updateUsername(newUsername: String) {
        _loginUiState.update { currentState ->
            currentState.copy(username = newUsername)
        }
    }

    fun updatePassword(newPassword: String) {
        _loginUiState.update { currentState ->
            currentState.copy(password = newPassword)
        }
    }


    // Metodos Privados
    private fun validarCampos(): Boolean = _loginUiState.value.username.isNotEmpty() && _loginUiState.value.password.isNotEmpty()



    // Metodos Auxiliares
    fun limpiarCampos() {
        _loginUiState.update { LoginUiState() }
    }


    // Metodos Publicos

    fun login() {
        viewModelScope.launch {

            if (!validarCampos()) {
                val validationErrors = listOf("Por favor, complete todos los campos")
                _loginEvents.emit(LoginEvent.ShowErrorDialog(validationErrors))
                return@launch
            }

            val data = AuthLoginDto(
                username = _loginUiState.value.username,
                password = _loginUiState.value.password
            )

            authRepository.login(data)
                .onStart {
                    _loginResult.value = UiState.Loading
                }
                .catch { e ->
                    val errorMessage = e.message ?: "Error de conexión"
                    _loginEvents.emit(LoginEvent.ShowErrorDialog(listOf(errorMessage)))
                    _loginResult.value = UiState.Error(errorMessage)
                }
                .collect { result ->
                    result.onSuccess { data ->
                        userDataStore.saveUserData(authResponse = data)
                        _loginResult.value = UiState.Success(data)
                    }
                    result.onFailure { e ->
                        val errorMessage = e.message ?: "Error del servidor"
                        _loginEvents.emit(LoginEvent.ShowErrorDialog(listOf(errorMessage)))
                        _loginResult.value = UiState.Error(errorMessage)
                    }
                }
        }
    }

}
