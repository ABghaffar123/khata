package com.khatabook.app.ui.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.khatabook.app.ui.responsive.LocalResponsiveSpacing
import com.khatabook.app.ui.responsive.LocalResponsiveTypography
import com.khatabook.app.ui.responsive.LocalWindowSize
import com.khatabook.app.ui.theme.BrandPrimary

/**
 * Bottom navigation bar items.
 *
 * 5 tabs: Home | Customers | Khata | Camera | Settings
 */
data class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = Screen.Home.route,
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomNavItem(
        route = Screen.Customers.route,
        label = "Customers",
        selectedIcon = Icons.Filled.People,
        unselectedIcon = Icons.Outlined.People
    ),
    BottomNavItem(
        route = Screen.Khata.route,
        label = "Khata",
        selectedIcon = Icons.Filled.Receipt,
        unselectedIcon = Icons.Outlined.Receipt
    ),
    BottomNavItem(
        route = Screen.Camera.route,
        label = "Scan",
        selectedIcon = Icons.Filled.CameraAlt,
        unselectedIcon = Icons.Outlined.CameraAlt
    ),
    BottomNavItem(
        route = Screen.Settings.route,
        label = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
)

/**
 * Adaptive navigation component.
 *
 * On compact/medium screens: Bottom navigation bar
 * On expanded/large screens: Navigation rail (side)
 *
 * This follows Material 3 adaptive navigation guidelines:
 * https://m3.material.io/components/navigation-bar/guidelines
 */
@Composable
fun KhataAdaptiveNavigation(
    navController: NavController
) {
    val windowSize = LocalWindowSize.current

    if (windowSize.showNavigationRail) {
        KhataNavigationRail(navController = navController)
    } else {
        KhataBottomNavBar(navController = navController)
    }
}

/**
 * Bottom navigation bar for compact/medium screens.
 */
@Composable
private fun KhataBottomNavBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val typography = LocalResponsiveTypography.current

    NavigationBar {
        bottomNavItems.forEach { item ->
            val selected = currentRoute == item.route

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = typography.labelSm,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                selected = selected,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BrandPrimary,
                    selectedTextColor = BrandPrimary,
                    indicatorColor = BrandPrimary.copy(alpha = 0.12f)
                ),
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

/**
 * Navigation rail for expanded/large screens.
 *
 * Shows as a vertical rail on the left side of the screen.
 * Icons with labels, compact layout.
 */
@Composable
private fun KhataNavigationRail(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val typography = LocalResponsiveTypography.current
    val spacing = LocalResponsiveSpacing.current

    NavigationRail(
        modifier = Modifier.fillMaxHeight()
    ) {
        bottomNavItems.forEach { item ->
            val selected = currentRoute == item.route

            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = typography.labelSm,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                selected = selected,
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = BrandPrimary,
                    selectedTextColor = BrandPrimary,
                    indicatorColor = BrandPrimary.copy(alpha = 0.12f)
                ),
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

/**
 * App content wrapper that handles adaptive navigation layout.
 *
 * On compact/medium: Scaffold with bottom bar
 * On expanded/large: Row with navigation rail + content
 */
@Composable
fun AdaptiveAppScaffold(
    navController: NavController,
    content: @Composable (Modifier) -> Unit
) {
    val windowSize = LocalWindowSize.current

    if (windowSize.showNavigationRail) {
        // Navigation rail layout
        Row(modifier = Modifier.fillMaxSize()) {
            KhataNavigationRail(navController = navController)
            content(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
    } else {
        // Bottom bar layout
        androidx.compose.material3.Scaffold(
            bottomBar = {
                KhataBottomNavBar(navController = navController)
            }
        ) { innerPadding ->
            content(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}
