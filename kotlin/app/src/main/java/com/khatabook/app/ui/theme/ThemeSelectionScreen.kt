package com.khatabook.app.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * ═══════════════════════════════════════════════════════════════════
 * THEME SELECTION SCREEN — Premium theme customization page
 * ═══════════════════════════════════════════════════════════════════
 *
 * Layout:
 *   1. Top bar with back + save
 *   2. Display mode toggle (Light / Dark / System)
 *   3. Theme preset cards (3-column grid)
 *   4. Accent color picker
 *   5. Live preview area
 *   6. Apply button
 *
 * Supports: English, Urdu, Urdu Roman
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionScreen(
    viewModel: ThemeSelectionViewModel,
    onBack: () -> Unit = {},
    onApply: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    // Accent color from selected theme
    val accentColor = state.selectedAccent.color

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Theme Settings",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    // Reset to default
                    TextButton(onClick = { viewModel.resetToDefault() }) {
                        Text(
                            text = "Reset",
                            color = accentColor,
                            fontSize = 13.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            // Apply button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .navigationBarsPadding()
            ) {
                Button(
                    onClick = {
                        viewModel.applyTheme()
                        onApply()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accentColor
                    ),
                    enabled = state.hasUnsavedChanges || !state.isApplied
                ) {
                    AnimatedVisibility(
                        visible = state.isApplied,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Applied!",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    AnimatedVisibility(
                        visible = !state.isApplied,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = "Apply Theme",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // ═══════════════════════════════════════════════════
            // 1. DISPLAY MODE TOGGLE
            // ═══════════════════════════════════════════════════
            DisplayModeToggle(
                selectedMode = state.displayMode,
                accentColor = accentColor,
                onModeSelected = { viewModel.selectDisplayMode(it) }
            )

            // ═══════════════════════════════════════════════════
            // 2. THEME PRESET CARDS
            // ═══════════════════════════════════════════════════
            Column {
                Text(
                    text = "Theme Presets",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // 3-column grid of theme cards
                val presets = KhataThemePresets.allPresets
                val rows = presets.chunked(3)

                rows.forEachIndexed { rowIndex, rowPresets ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowPresets.forEach { preset ->
                            ThemePresetCard(
                                preset = preset,
                                isSelected = state.selectedPresetId == preset.id,
                                onClick = { viewModel.selectPreset(preset.id) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        // Fill empty cells if last row has < 3 items
                        repeat(3 - rowPresets.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    if (rowIndex < rows.lastIndex) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            // ═══════════════════════════════════════════════════
            // 3. ACCENT COLOR PICKER
            // ═══════════════════════════════════════════════════
            AccentColorPicker(
                selectedIndex = state.selectedAccentIndex,
                onColorSelected = { viewModel.selectAccent(it) }
            )

            // ═══════════════════════════════════════════════════
            // 4. LIVE PREVIEW
            // ═══════════════════════════════════════════════════
            ThemePreview(
                theme = state.previewTheme
            )

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
