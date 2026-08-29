package com.khatabook.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Shape system for Khata One.
 *
 * Consistent rounded corners across the app:
 * - Small: Chips, badges, small buttons
 * - Medium: Cards, inputs, list items
 * - Large: Bottom sheets, dialogs
 * - Extra Large: Full-width cards, hero sections
 */
val KhataShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)
