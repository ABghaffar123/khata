package com.khatabook.app.ui.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.khatabook.app.ui.responsive.LocalResponsiveSpacing
import com.khatabook.app.ui.responsive.LocalResponsiveTypography
import com.khatabook.app.ui.responsive.LocalWindowSize
import com.khatabook.app.ui.responsive.MaxWidthContainer
import com.khatabook.app.ui.responsive.ResponsiveCard
import com.khatabook.app.ui.responsive.ResponsiveSettingsItem
import com.khatabook.app.ui.responsive.WindowWidthSizeClass

/**
 * Settings screen — Fully adaptive.
 *
 * COMPACT (< 600dp): Single column, stacked sections
 * EXPANDED (840dp+): Two-column grid layout
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateToLanguage: () -> Unit = {},
    onNavigateToTheme: () -> Unit = {},
    onNavigateToSecurity: () -> Unit = {},
    onNavigateToBackup: () -> Unit = {},
    onNavigateToAbout: () -> Unit = {}
) {
    val windowSize = LocalWindowSize.current
    val spacing = LocalResponsiveSpacing.current
    val typography = LocalResponsiveTypography.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = typography.h2
                        ),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        if (windowSize.widthSizeClass >= WindowWidthSizeClass.Expanded) {
            // Two-column layout on expanded screens
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(spacing.sectionGap)
            ) {
                item {
                    MaxWidthContainer {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(spacing.sectionGap)
                        ) {
                            // Left column: Appearance + Data
                            Column(modifier = Modifier.weight(1f)) {
                                SettingsSection(title = "Appearance") {
                                    SettingsGroupCard {
                                        ResponsiveSettingsItem(
                                            icon = Icons.Default.Language,
                                            title = "Language",
                                            subtitle = "English",
                                            onClick = onNavigateToLanguage
                                        )
                                        ResponsiveSettingsItem(
                                            icon = Icons.Default.Brightness6,
                                            title = "Theme",
                                            subtitle = "System Default",
                                            onClick = onNavigateToTheme
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(spacing.sectionGap))
                                SettingsSection(title = "Data Management") {
                                    SettingsGroupCard {
                                        ResponsiveSettingsItem(
                                            icon = Icons.Default.Storage,
                                            title = "Backup Center",
                                            subtitle = "Backup and restore your data",
                                            onClick = onNavigateToBackup
                                        )
                                    }
                                }
                            }
                            // Right column: Security + About
                            Column(modifier = Modifier.weight(1f)) {
                                SettingsSection(title = "Security") {
                                    SettingsGroupCard {
                                        ResponsiveSettingsItem(
                                            icon = Icons.Default.Security,
                                            title = "Security Settings",
                                            subtitle = "PIN, Biometric, Auto-lock",
                                            onClick = onNavigateToSecurity
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(spacing.sectionGap))
                                SettingsSection(title = "About") {
                                    SettingsGroupCard {
                                        ResponsiveSettingsItem(
                                            icon = Icons.Default.Info,
                                            title = "About Khata One",
                                            subtitle = "Version 1.0.0",
                                            onClick = onNavigateToAbout
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // Single column on compact/medium
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(spacing.sectionGap / 2)
            ) {
                item {
                    MaxWidthContainer {
                        SettingsSection(title = "Appearance") {
                            SettingsGroupCard {
                                ResponsiveSettingsItem(
                                    icon = Icons.Default.Language,
                                    title = "Language",
                                    subtitle = "English",
                                    onClick = onNavigateToLanguage
                                )
                                ResponsiveSettingsItem(
                                    icon = Icons.Default.Brightness6,
                                    title = "Theme",
                                    subtitle = "System Default",
                                    onClick = onNavigateToTheme
                                )
                            }
                        }
                    }
                }
                item {
                    MaxWidthContainer {
                        SettingsSection(title = "Security") {
                            SettingsGroupCard {
                                ResponsiveSettingsItem(
                                    icon = Icons.Default.Security,
                                    title = "Security Settings",
                                    subtitle = "PIN, Biometric, Auto-lock",
                                    onClick = onNavigateToSecurity
                                )
                            }
                        }
                    }
                }
                item {
                    MaxWidthContainer {
                        SettingsSection(title = "Data Management") {
                            SettingsGroupCard {
                                ResponsiveSettingsItem(
                                    icon = Icons.Default.Storage,
                                    title = "Backup Center",
                                    subtitle = "Backup and restore your data",
                                    onClick = onNavigateToBackup
                                )
                            }
                        }
                    }
                }
                item {
                    MaxWidthContainer {
                        SettingsSection(title = "About") {
                            SettingsGroupCard {
                                ResponsiveSettingsItem(
                                    icon = Icons.Default.Info,
                                    title = "About Khata One",
                                    subtitle = "Version 1.0.0",
                                    onClick = onNavigateToAbout
                                )
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(spacing.screenVertical))
                }
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    val typography = LocalResponsiveTypography.current
    val spacing = LocalResponsiveSpacing.current

    Column(
        modifier = Modifier.padding(vertical = spacing.itemGap)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = typography.labelLg
            ),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = spacing.itemGap)
        )
        content()
    }
}

@Composable
private fun SettingsGroupCard(
    content: @Composable () -> Unit
) {
    ResponsiveCard {
        content()
    }
}
