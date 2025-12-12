@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package com.app.inventario.ui.screen.auth


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    val loginResult by authViewModel.loginResult.collectAsState()

    var showCustomDialog by remember { mutableStateOf(false) }
    var erroresGenerales by remember { mutableStateOf(emptyList<String>()) }
    var isLoading by remember { mutableStateOf(false) }

    // Siempre llamar al el event del shared flow en la primera recomposicion del composable con Unit
    LaunchedEffect(Unit) {
        authViewModel.loginEvents.collect { event ->
            when (event) {
                is LoginEvent.ShowErrorDialog -> {
                    erroresGenerales = event.messages
                    showCustomDialog = true
                }
            }
        }
    }

    LaunchedEffect(loginResult) {
        when (loginResult) {
            is UiState.Loading -> { isLoading = true }
            is UiState.Error -> {
                isLoading = false
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
            onClose = { showCustomDialog = false }
        )
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ResponsiveLoginContent(
            maxWidth = maxWidth,
            authViewModel = authViewModel
        )
    }
}

@Composable
fun ResponsiveLoginContent(
    maxWidth: Dp,
    authViewModel: AuthViewModel
) {

    val loginUiState by authViewModel.loginUiState.collectAsStateWithLifecycle()

    val isMobile = maxWidth < 600.dp
    val horizontalPadding = if (isMobile) 24.dp else 48.dp
    val verticalSpacing = if (isMobile) 16.dp else 24.dp
    val titleSize = if (isMobile) 32.sp else 40.sp
    val iconSize = if (isMobile) 80.dp else 120.dp

    if (isMobile) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(iconSize)
            )

            Spacer(Modifier.height(verticalSpacing))

            Text(
                text = "Login",
                fontSize = titleSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(verticalSpacing * 2))

            CustomOutlinedTextField(
                value = loginUiState.username,
                onValueChange = { authViewModel.updateUsername(it) },
                label = "Username",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(verticalSpacing))

            CustomOutlinedTextField(
                value = loginUiState.password,
                onValueChange = { authViewModel.updatePassword(it) },
                label = "Password",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(verticalSpacing * 1.5f))

            CustomButton(
                text = "Login",
                onClick = { authViewModel.login() }
            )
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontalPadding)
                .verticalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(iconSize)
                )

                Spacer(Modifier.height(verticalSpacing))

                Text(
                    text = "Bienvenido",
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Inicia sesión para continuar",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    fontSize = titleSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(Modifier.height(verticalSpacing * 2))

                CustomOutlinedTextField(
                    value = loginUiState.username,
                    onValueChange = { authViewModel.updateUsername(it) },
                    label = "Username",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(verticalSpacing))

                CustomOutlinedTextField(
                    value = loginUiState.password,
                    onValueChange = { authViewModel.updatePassword(it) },
                    label = "Password",
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(verticalSpacing * 1.5f))

                CustomButton(
                    text = "Login",
                    onClick = { authViewModel.login() }
                )
            }
        }
    }
}