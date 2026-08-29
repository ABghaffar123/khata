package com.khatabook.app.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.khatabook.app.ui.responsive.LocalResponsiveTypography
import com.khatabook.app.ui.responsive.LocalWindowSize
import com.khatabook.app.ui.screen.home.HomeScreen
import com.khatabook.app.ui.screen.settings.SettingsScreen

/**
 * Navigation graph for Khata One — Adaptive.
 *
 * Animation strategy:
 * - Tab switches: Fade (fast, non-disruptive)
 * - Push screens: Slide from right (standard Android)
 * - Modal screens: Slide up from bottom
 *
 * @param modifier Modifier applied to the NavHost container
 */
@Composable
fun KhataNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = {
            fadeIn(animationSpec = tween(200)) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(200))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(200))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(200)) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            )
        }
    ) {
        // ═══ Main Tabs ═══
        composable(
            route = Screen.Home.route,
            enterTransition = { fadeIn(animationSpec = tween(200)) },
            exitTransition = { fadeOut(animationSpec = tween(200)) }
        ) {
            HomeScreen(
                onNavigateToCustomers = {
                    navController.navigate(Screen.Customers.route)
                },
                onNavigateToNewEntry = {
                    navController.navigate(Screen.NewTransaction.createRoute())
                },
                onNavigateToScan = {
                    navController.navigate(Screen.Camera.route)
                }
            )
        }

        composable(
            route = Screen.Customers.route,
            enterTransition = { fadeIn(animationSpec = tween(200)) },
            exitTransition = { fadeOut(animationSpec = tween(200)) }
        ) {
            AdaptivePlaceholderScreen("Customers", "👥")
        }

        composable(
            route = Screen.Khata.route,
            enterTransition = { fadeIn(animationSpec = tween(200)) },
            exitTransition = { fadeOut(animationSpec = tween(200)) }
        ) {
            AdaptivePlaceholderScreen("Khata Register", "📒")
        }

        composable(
            route = Screen.Camera.route,
            enterTransition = { fadeIn(animationSpec = tween(200)) },
            exitTransition = { fadeOut(animationSpec = tween(200)) }
        ) {
            AdaptivePlaceholderScreen("Scan Khata", "📷")
        }

        composable(
            route = Screen.Settings.route,
            enterTransition = { fadeIn(animationSpec = tween(200)) },
            exitTransition = { fadeOut(animationSpec = tween(200)) }
        ) {
            SettingsScreen(
                onNavigateToLanguage = {
                    navController.navigate(Screen.LanguageSettings.route)
                },
                onNavigateToTheme = {
                    navController.navigate(Screen.ThemeSettings.route)
                },
                onNavigateToSecurity = {
                    navController.navigate(Screen.SecuritySettings.route)
                },
                onNavigateToBackup = {
                    navController.navigate(Screen.BackupCenter.route)
                },
                onNavigateToAbout = {
                    navController.navigate(Screen.About.route)
                }
            )
        }

        // ═══ Customer Flow ═══
        composable(
            route = Screen.CustomerDetail.route,
            arguments = listOf(navArgument("customerId") { type = NavType.LongType })
        ) {
            AdaptivePlaceholderScreen("Customer Detail", "👤")
        }

        composable(route = Screen.AddCustomer.route) {
            AdaptivePlaceholderScreen("Add Customer", "➕")
        }

        composable(
            route = Screen.EditCustomer.route,
            arguments = listOf(navArgument("customerId") { type = NavType.LongType })
        ) {
            AdaptivePlaceholderScreen("Edit Customer", "✏️")
        }

        // ═══ Transaction Flow ═══
        composable(
            route = Screen.NewTransaction.route,
            arguments = listOf(
                navArgument("customerId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            AdaptivePlaceholderScreen("New Transaction", "💰")
        }

        // ═══ OCR Flow ═══
        composable(route = Screen.OcrScan.route) {
            AdaptivePlaceholderScreen("OCR Scan", "📷")
        }

        composable(
            route = Screen.OcrReview.route,
            arguments = listOf(navArgument("captureId") { type = NavType.LongType })
        ) {
            AdaptivePlaceholderScreen("OCR Review", "📝")
        }

        // ═══ Search ═══
        composable(route = Screen.Search.route) {
            AdaptivePlaceholderScreen("Search", "🔍")
        }

        // ═══ Reports ═══
        composable(route = Screen.Reports.route) {
            AdaptivePlaceholderScreen("Reports", "📊")
        }

        // ═══ Settings Sub-screens ═══
        composable(route = Screen.LanguageSettings.route) {
            AdaptivePlaceholderScreen("Language Settings", "🌐")
        }

        composable(route = Screen.ThemeSettings.route) {
            AdaptivePlaceholderScreen("Theme Settings", "🎨")
        }

        composable(route = Screen.SecuritySettings.route) {
            AdaptivePlaceholderScreen("Security Settings", "🔒")
        }

        composable(route = Screen.BackupCenter.route) {
            AdaptivePlaceholderScreen("Backup Center", "💾")
        }

        composable(route = Screen.About.route) {
            AdaptivePlaceholderScreen("About", "ℹ️")
        }
    }
}

/**
 * Adaptive placeholder screen for unimplemented screens.
 * Scales text and layout based on screen size.
 */
@Composable
private fun AdaptivePlaceholderScreen(
    title: String,
    icon: String = "🚧"
) {
    val typography = LocalResponsiveTypography.current
    val windowSize = LocalWindowSize.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$icon\n$title\n\nComing Soon",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = if (windowSize.isCompact) typography.h3 else typography.h2
            ),
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
            textAlign = TextAlign.Center
        )
    }
}
