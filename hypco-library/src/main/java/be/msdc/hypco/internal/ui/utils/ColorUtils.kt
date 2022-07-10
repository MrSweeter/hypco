package be.msdc.hypco.internal.ui.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
internal fun getCardBackgroundColor() = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.25f)