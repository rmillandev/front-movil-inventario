package com.app.inventario.ui.screen.categoria

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.inventario.data.remote.dto.category.CategoriaDto
import com.app.inventario.data.remote.dto.success.ReadResponseDto
import com.app.inventario.data.repository.CategoriaRepository
import com.app.inventario.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriaViewModel @Inject constructor(
    private val categoriaRepository: CategoriaRepository
) : ViewModel() {

    // Variables
    var id by mutableStateOf(0)
    var nombre by mutableStateOf("")
    var descripcion by mutableStateOf("")


    // Estado UI
    private val _getAllCategoriaResponse = MutableStateFlow<UiState<ReadResponseDto<CategoriaDto>>>(UiState.IdLe)
    val getAllCategoriaResponse: StateFlow<UiState<ReadResponseDto<CategoriaDto>>> = _getAllCategoriaResponse

    private val _getByIdCategoriaResponse = MutableStateFlow<UiState<CategoriaDto>>(UiState.IdLe)
    val getByIdCategoriaResponse: StateFlow<UiState<CategoriaDto>> = _getByIdCategoriaResponse



    // Metodos Privados




    // Metodos Auxiliares




    // Metodos Publicos

    fun getAll(pageNumber: Int? = null, pageSize: Int? = null) {
        viewModelScope.launch {
            categoriaRepository.getAll(pageNumber, pageSize)
                .onStart {
                    _getAllCategoriaResponse.value = UiState.Loading
                }
                .catch { e ->
                    _getAllCategoriaResponse.value = UiState.Error(e.message ?: "Error desconocido")
                }
                .collect { result ->
                    result
                        .onSuccess { data ->
                            _getAllCategoriaResponse.value = UiState.Success(data)
                        }
                        .onFailure { e ->
                            _getAllCategoriaResponse.value = UiState.Error(e.message ?: "Error desconocido")
                        }
                }
        }
    }

    fun getById(id: Int) {
        viewModelScope.launch {
            categoriaRepository.getById(id)
                .onStart {
                    _getByIdCategoriaResponse.value = UiState.Loading
                }
                .catch { e ->
                    _getByIdCategoriaResponse.value = UiState.Error(e.message ?: "Error desconocido")
                }
                .collect { result ->
                    result
                        .onSuccess { data ->
                            _getByIdCategoriaResponse.value = UiState.Success(data)
                        }
                        .onFailure { e ->
                            _getByIdCategoriaResponse.value = UiState.Error(e.message ?: "Error desconocido")
                        }
                }
        }
    }


}