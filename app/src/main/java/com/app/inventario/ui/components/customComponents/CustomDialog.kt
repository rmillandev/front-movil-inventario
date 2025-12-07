package com.app.inventario.ui.components.customComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.inventario.ui.enums.DialogType

@Composable
fun CustomDialog(
    title: String,
    messages: String,
    type: DialogType,
    onClose: () -> Unit,
    confirmText: String = "Cerrar",
    dismissOnBack: Boolean = true
) {
    Dialog(
        onDismissRequest = {
            if (dismissOnBack) onClose()
        },
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBack,
            dismissOnClickOutside = false
        )
    ) {
        val iconData = when (type) {
            DialogType.SUCCESS -> Icons.Default.CheckCircle
            DialogType.ERROR -> Icons.Default.Error
            DialogType.INFO -> Icons.Default.Info
            DialogType.WARNING -> Icons.Default.Warning
        }

        val iconColor = when (type) {
            DialogType.SUCCESS -> MaterialTheme.colorScheme.tertiary
            DialogType.ERROR -> MaterialTheme.colorScheme.error
            DialogType.INFO -> MaterialTheme.colorScheme.primary
            DialogType.WARNING -> MaterialTheme.colorScheme.secondary
        }

        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    imageVector = iconData,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = messages,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = confirmText,
                    onClick = { onClose() }
                )

            }
        }
    }
}
