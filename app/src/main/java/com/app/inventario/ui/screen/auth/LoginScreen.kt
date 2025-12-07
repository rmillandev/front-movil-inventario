package com.app.inventario.ui.screen.auth


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.app.inventario.ui.components.CircularProgressBarBoxShadow
import com.app.inventario.ui.components.customComponents.CustomButton
import com.app.inventario.ui.components.customComponents.CustomDialog
import com.app.inventario.ui.components.customComponents.CustomOutlinedTextField
import com.app.inventario.ui.enums.DialogType
import com.app.inventario.ui.state.UiState

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit
) {

    val authViewModel: AuthViewModel = hiltViewModel()

    val loginState by authViewModel.loginState.collectAsState()
    val erroresState by authViewModel.errores.collectAsState()

    var showCustomDialog by remember { mutableStateOf(false) }
    var erroresGenerales by remember { mutableStateOf(emptyList<String>()) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(erroresState) {
        if (erroresState.isNotEmpty()) {
            erroresGenerales = erroresState
            showCustomDialog = true
        }
    }

    LaunchedEffect(loginState) {
        when (loginState) {
            is UiState.Loading -> { isLoading = true }
            is UiState.Error -> {
                isLoading = false
                val error = (loginState as UiState.Error).message
                erroresGenerales = listOf(error)
                showCustomDialog = true
            }
            is UiState.Success -> {
                isLoading = false
                navigateToHome()
                authViewModel.limpiarCampos()
            } else -> {
                isLoading = false
            }
        }
    }

    CircularProgressBarBoxShadow(isShowing = isLoading)

    if (showCustomDialog) {
        CustomDialog(
            title = "Error",
            messages = erroresGenerales.joinToString("\n"),
            type = DialogType.ERROR,
            onClose = {
                authViewModel.limpiarErrorres()
                showCustomDialog = false
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {

            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(80.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Login",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(32.dp))

            CustomOutlinedTextField(
                value = authViewModel.username,
                onValueChange = { authViewModel.username = it },
                label = "Username",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            CustomOutlinedTextField(
                value = authViewModel.password,
                onValueChange = { authViewModel.password = it },
                label = "Password",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            CustomButton(
                text = "Login",
                onClick = { authViewModel.login() }
            )
        }
    }

}