package com.khatabook.app.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * ═══════════════════════════════════════════════════════════════════
 * ACCENT COLOR PICKER — Horizontal color selection row
 * ═══════════════════════════════════════════════════════════════════
 *
 * ┌──────────────────────────────────────────────┐
 * │  ●  ●  ●  ●  ●  ●  ●  ●  ●  ●  ●  ●       │
 * │  Bl Gr Te Cy Bl Pu Vi Pi Or Re Br Gr          │
 * │                                               │
 * │  Selected: 32dp, filled, white checkmark ✓    │
 * │  Unselected: 28dp, filled, no checkmark       │
 * │  Spacing: 8dp between colors                  │
 * └──────────────────────────────────────────────┘
 */
@Composable
fun AccentColorPicker(
    selectedIndex: Int,
    onColorSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = KhataThemePresets.accentColors

    Column(modifier = modifier.fillMaxWidth()) {
        // Section header
        Text(
            text = "Accent Color",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Color circles grid — 2 rows of 6
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            userScrollEnabled = false
        ) {
            itemsIndexed(colors) { index, accent ->
                AccentCircle(
                    color = accent.color,
                    isSelected = index == selectedIndex,
                    onClick = { onColorSelected(index) }
                )
            }
        }
    }
}

@Composable
private fun AccentCircle(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val size by animateDpAsState(
        targetValue = if (isSelected) 36.dp else 30.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "circle_size"
    )

    val borderColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else Color.Transparent,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "circle_border"
    )

    Box(
        modifier = modifier
            .size(size)
            .shadow(
                elevation = if (isSelected) 6.dp else 2.dp,
                shape = CircleShape,
                ambientColor = color.copy(alpha = 0.3f),
                spotColor = color.copy(alpha = 0.5f)
            )
            .clip(CircleShape)
            .background(color)
            .border(width = 3.dp, color = borderColor, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Selected",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
