package com.khatabook.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.khatabook.app.ui.navigation.KhataAdaptiveNavigation
import com.khatabook.app.ui.navigation.KhataNavGraph
import com.khatabook.app.ui.navigation.Screen
import com.khatabook.app.ui.responsive.LocalWindowSize
import com.khatabook.app.ui.theme.KhataTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity — Hosts the entire Compose UI with adaptive layout.
 *
 * LIFECYCLE:
 * - Created on cold start (after SplashActivity)
 * - Resumes directly on warm resume (no splash)
 * - Handles config changes (rotation, language)
 *
 * ADAPTIVE LAYOUT:
 * ┌─────────────────────────────────────────────────────┐
 * │ COMPACT/MEDIUM (< 840dp):                          │
 * │ ┌───────────────────────────────────────────────┐  │
 * │ │              Content                          │  │
 * │ │         (NavGraph screens)                    │  │
 * │ ├───────────────────────────────────────────────┤  │
 * │ │  Home | Customers | Khata | Scan | Settings   │  │
 * │ └───────────────────────────────────────────────┘  │
 * ├─────────────────────────────────────────────────────┤
 * │ EXPANDED/LARGE (>= 840dp):                         │
 * │ ┌────┬──────────────────────────────────────────┐  │
 * │ │    │              Content                      │  │
 * │ │ H  │         (NavGraph screens)                │  │
 * │ │ C  │                                           │  │
 * │ │ K  │                                           │  │
 * │ │ S  │                                           │  │
 * │ │    │                                           │  │
 * │ └────┴──────────────────────────────────────────┘  │
 * └─────────────────────────────────────────────────────┘
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KhataTheme {
                KhataApp()
            }
        }
    }
}

/**
 * Main app composable with adaptive navigation.
 */
@Composable
private fun KhataApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val windowSize = LocalWindowSize.current

    // Routes where navigation should be visible
    val showNavigation = currentRoute in listOf(
        Screen.Home.route,
        Screen.Customers.route,
        Screen.Khata.route,
        Screen.Camera.route,
        Screen.Settings.route
    )

    // Routes that are full-screen (no navigation)
    val isFullScreen = currentRoute in listOf(
        Screen.LanguageSelection.route
    )

    if (windowSize.showNavigationRail && showNavigation && !isFullScreen) {
        // ═══ EXPANDED/LARGE: Navigation Rail Layout ═══
        Row(modifier = Modifier.fillMaxSize()) {
            KhataAdaptiveNavigation(navController = navController)
            Scaffold(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) { innerPadding ->
                KhataNavGraph(
                    navController = navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    } else {
        // ═══ COMPACT/MEDIUM: Bottom Bar Layout ═══
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (showNavigation && !isFullScreen) {
                    KhataAdaptiveNavigation(navController = navController)
                }
            }
        ) { innerPadding ->
            KhataNavGraph(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
