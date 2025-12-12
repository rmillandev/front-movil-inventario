package com.app.inventario.ui.screen.auth

sealed interface LoginEvent {

    data class ShowErrorDialog(
        val messages: List<String>
    ) : LoginEvent


}
