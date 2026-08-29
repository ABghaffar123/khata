package com.khatabook.app.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.khatabook.app.ui.responsive.LocalResponsiveSpacing
import com.khatabook.app.ui.responsive.LocalResponsiveTypography
import com.khatabook.app.ui.responsive.LocalWindowSize
import com.khatabook.app.ui.responsive.MaxWidthContainer
import com.khatabook.app.ui.responsive.ResponsiveActionCard
import com.khatabook.app.ui.responsive.ResponsiveCard
import com.khatabook.app.ui.responsive.ResponsiveListItem
import com.khatabook.app.ui.responsive.ResponsiveSection
import com.khatabook.app.ui.responsive.StatsRow
import com.khatabook.app.ui.responsive.WindowWidthSizeClass
import com.khatabook.app.ui.theme.BrandPrimary
import com.khatabook.app.ui.theme.ErrorRed
import com.khatabook.app.ui.theme.SuccessGreen
import com.khatabook.app.util.getGreeting
import com.khatabook.app.util.toCurrency

/**
 * Home Dashboard screen — Fully adaptive.
 *
 * COMPACT (< 600dp): Single column, bottom nav
 * MEDIUM (600-840dp): Wider cards, bottom nav
 * EXPANDED (840dp+): Side-by-side sections, nav rail
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToCustomers: () -> Unit = {},
    onNavigateToNewEntry: () -> Unit = {},
    onNavigateToScan: () -> Unit = {}
) {
    val windowSize = LocalWindowSize.current
    val spacing = LocalResponsiveSpacing.current
    val typography = LocalResponsiveTypography.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = getGreeting(),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = typography.bodyMd
                            ),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        Text(
                            text = "Khata One",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontSize = typography.h2
                            ),
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Search */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(spacing.buttonIconSize)
                        )
                    }
                    IconButton(onClick = { /* TODO: Notifications */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(spacing.buttonIconSize)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToNewEntry,
                containerColor = BrandPrimary,
                contentColor = Color.White,
                modifier = Modifier.size(spacing.fabSize)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "New Entry",
                    modifier = Modifier.size(spacing.buttonIconSize + 4.dp)
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(spacing.sectionGap)
        ) {
            // ═══ Stats Cards ═══
            // StatsRow uses Arrangement.spacedBy — no Spacer needed between cards
            item {
                MaxWidthContainer {
                    StatsRow {
                        Box(modifier = Modifier.weight(1f)) {
                            com.khatabook.app.ui.responsive.ResponsiveStatCard(
                                title = "Total Dues",
                                amount = 45_000.0.toCurrency(),
                                icon = Icons.Default.ArrowUpward,
                                iconColor = ErrorRed,
                                backgroundColor = MaterialTheme.colorScheme.errorContainer
                            )
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            com.khatabook.app.ui.responsive.ResponsiveStatCard(
                                title = "Today",
                                amount = 12_000.0.toCurrency(),
                                icon = Icons.Default.ArrowDownward,
                                iconColor = SuccessGreen,
                                backgroundColor = MaterialTheme.colorScheme.secondaryContainer
                            )
                        }
                        if (windowSize.widthSizeClass >= WindowWidthSizeClass.Expanded) {
                            Box(modifier = Modifier.weight(1f)) {
                                com.khatabook.app.ui.responsive.ResponsiveStatCard(
                                    title = "This Month",
                                    amount = 1_25_000.0.toCurrency(),
                                    icon = Icons.Default.ArrowDownward,
                                    iconColor = BrandPrimary,
                                    backgroundColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            }
                        }
                    }
                }
            }

            // ═══ Quick Actions ═══
            // Row uses Arrangement.spacedBy — no Spacer needed between cards
            item {
                MaxWidthContainer {
                    ResponsiveSection(title = "Quick Actions") {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(spacing.actionCardGap)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                ResponsiveActionCard(
                                    title = "New Entry",
                                    icon = Icons.Default.Add,
                                    onClick = onNavigateToNewEntry
                                )
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                ResponsiveActionCard(
                                    title = "Scan",
                                    icon = Icons.Default.CameraAlt,
                                    onClick = onNavigateToScan
                                )
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                ResponsiveActionCard(
                                    title = "View All",
                                    icon = Icons.Default.Search,
                                    onClick = onNavigateToCustomers
                                )
                            }
                            if (windowSize.widthSizeClass >= WindowWidthSizeClass.Expanded) {
                                Box(modifier = Modifier.weight(1f)) {
                                    ResponsiveActionCard(
                                        title = "Reports",
                                        icon = Icons.Default.Notifications,
                                        onClick = { /* TODO */ }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // ═══ Top Debtors + Recent Activity ═══
            if (windowSize.widthSizeClass >= WindowWidthSizeClass.Expanded) {
                // Two-column layout on expanded screens
                item {
                    MaxWidthContainer {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(spacing.sectionGap)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                ResponsiveSection(
                                    title = "Top Debtors",
                                    actionText = "View All",
                                    onAction = onNavigateToCustomers
                                ) {
                                    DebtorsList(
                                        names = listOf("Ahmed Khan", "Ali Khan", "Usman Sheikh"),
                                        amounts = listOf(5_000.0, 3_000.0, 2_500.0)
                                    )
                                }
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                ResponsiveSection(
                                    title = "Recent Activity",
                                    actionText = "View All",
                                    onAction = { /* TODO */ }
                                ) {
                                    ActivityList()
                                }
                            }
                        }
                    }
                }
            } else {
                // Single column on compact/medium
                item {
                    MaxWidthContainer {
                        ResponsiveSection(
                            title = "Top Debtors",
                            actionText = "View All",
                            onAction = onNavigateToCustomers
                        ) {
                            DebtorsList(
                                names = listOf("Ahmed Khan", "Ali Khan", "Usman Sheikh"),
                                amounts = listOf(5_000.0, 3_000.0, 2_500.0)
                            )
                        }
                    }
                }
                item {
                    MaxWidthContainer {
                        ResponsiveSection(
                            title = "Recent Activity",
                            actionText = "View All",
                            onAction = { /* TODO */ }
                        ) {
                            ActivityList()
                        }
                    }
                }
            }

            // Bottom spacer for FAB
            item {
                Spacer(modifier = Modifier.height(spacing.fabSize + spacing.screenVertical))
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════
// LIST COMPONENTS
// ═══════════════════════════════════════════════════════════

@Composable
private fun DebtorsList(
    names: List<String>,
    amounts: List<Double>
) {
    val spacing = LocalResponsiveSpacing.current

    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.itemGap)
    ) {
        names.forEachIndexed { index, name ->
            ResponsiveListItem(
                title = name,
                amount = amounts[index].toCurrency()
            )
        }
    }
}

@Composable
private fun ActivityList() {
    val spacing = LocalResponsiveSpacing.current
    val typography = LocalResponsiveTypography.current

    val descriptions = listOf("Received from Ahmed", "Credit to Ali", "Cash sale to Usman")
    val amounts = listOf(2_000.0, 5_000.0, 1_500.0)
    val isCredit = listOf(false, true, false)

    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.itemGap)
    ) {
        descriptions.forEachIndexed { index, description ->
            ResponsiveCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(spacing.listItemIconSize - 8.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isCredit[index]) ErrorRed.copy(alpha = 0.12f)
                                    else SuccessGreen.copy(alpha = 0.12f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isCredit[index]) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                                contentDescription = null,
                                tint = if (isCredit[index]) ErrorRed else SuccessGreen,
                                modifier = Modifier.size(spacing.buttonIconSize - 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(spacing.itemGap))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = description,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = typography.bodyMd
                                ),
                                fontWeight = FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Today",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = typography.bodySm
                                ),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }
                    }
                    Text(
                        text = "${if (isCredit[index]) "-" else "+"}${amounts[index].toCurrency()}",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = typography.amountSm
                        ),
                        fontWeight = FontWeight.Bold,
                        color = if (isCredit[index]) ErrorRed else SuccessGreen,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
