package com.khatabook.app.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * ═══════════════════════════════════════════════════════════════════
 * THEME PRESETS — 8 ready-made theme configurations
 * ═══════════════════════════════════════════════════════════════════
 *
 * Each preset defines a complete color scheme:
 *   - Primary / PrimaryVariant
 *   - Secondary / SecondaryVariant
 *   - Background / Surface / SurfaceVariant
 *   - Error
 *   - OnPrimary / OnSecondary / OnBackground / OnSurface
 *   - Accent (for highlights, badges, active indicators)
 */

data class KhataThemePreset(
    val id: String,
    val nameResKey: String,         // Key for string resource lookup
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val error: Color,
    val accent: Color,
    val isDarkTheme: Boolean = false,
    val previewTopBarColor: Color = primary,
    val previewCardColor: Color = surface,
    val previewNavColor: Color = surfaceVariant
)

object KhataThemePresets {

    // ═══════════════════════════════════════════════════════════════
    // 1. TRUST BLUE — Professional, reliable (Default)
    // ═══════════════════════════════════════════════════════════════
    val TrustBlue = KhataThemePreset(
        id = "trust_blue",
        nameResKey = "theme_trust_blue",
        primary = Color(0xFF1A73E8),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFD2E3FC),
        secondary = Color(0xFF00BFA5),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFB2F5EA),
        background = Color(0xFFFAFAFA),
        onBackground = Color(0xFF1C1B1F),
        surface = Color.White,
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFF1F3F4),
        onSurfaceVariant = Color(0xFF444746),
        error = Color(0xFFD32F2F),
        accent = Color(0xFF00BFA5),
        previewTopBarColor = Color(0xFF1A73E8),
        previewCardColor = Color.White,
        previewNavColor = Color(0xFFF1F3F4)
    )

    // ═══════════════════════════════════════════════════════════════
    // 2. GROWTH TEAL — Fresh, money-focused
    // ═══════════════════════════════════════════════════════════════
    val GrowthTeal = KhataThemePreset(
        id = "growth_teal",
        nameResKey = "theme_growth_teal",
        primary = Color(0xFF00BFA5),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFB2F5EA),
        secondary = Color(0xFF1A73E8),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFD2E3FC),
        background = Color(0xFFF5FFFE),
        onBackground = Color(0xFF1C1B1F),
        surface = Color.White,
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFE0F2F1),
        onSurfaceVariant = Color(0xFF444746),
        error = Color(0xFFD32F2F),
        accent = Color(0xFFFF9800),
        previewTopBarColor = Color(0xFF00BFA5),
        previewCardColor = Color.White,
        previewNavColor = Color(0xFFE0F2F1)
    )

    // ═══════════════════════════════════════════════════════════════
    // 3. FRESH GREEN — Natural, positive
    // ═══════════════════════════════════════════════════════════════
    val FreshGreen = KhataThemePreset(
        id = "fresh_green",
        nameResKey = "theme_fresh_green",
        primary = Color(0xFF2E7D32),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFC8E6C9),
        secondary = Color(0xFFFF9800),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFFFE0B2),
        background = Color(0xFFF1F8E9),
        onBackground = Color(0xFF1C1B1F),
        surface = Color.White,
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFDCEDC8),
        onSurfaceVariant = Color(0xFF444746),
        error = Color(0xFFD32F2F),
        accent = Color(0xFFFF9800),
        previewTopBarColor = Color(0xFF2E7D32),
        previewCardColor = Color.White,
        previewNavColor = Color(0xFFDCEDC8)
    )

    // ═══════════════════════════════════════════════════════════════
    // 4. WARM ROSE — Warm, friendly
    // ═══════════════════════════════════════════════════════════════
    val WarmRose = KhataThemePreset(
        id = "warm_rose",
        nameResKey = "theme_warm_rose",
        primary = Color(0xFFE91E63),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFFCE4EC),
        secondary = Color(0xFF9C27B0),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFEDE7F6),
        background = Color(0xFFFFF0F3),
        onBackground = Color(0xFF1C1B1F),
        surface = Color.White,
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFFCE4EC),
        onSurfaceVariant = Color(0xFF444746),
        error = Color(0xFFD32F2F),
        accent = Color(0xFF9C27B0),
        previewTopBarColor = Color(0xFFE91E63),
        previewCardColor = Color.White,
        previewNavColor = Color(0xFFFCE4EC)
    )

    // ═══════════════════════════════════════════════════════════════
    // 5. ROYAL PURPLE — Bold, premium
    // ═══════════════════════════════════════════════════════════════
    val RoyalPurple = KhataThemePreset(
        id = "royal_purple",
        nameResKey = "theme_royal_purple",
        primary = Color(0xFF7C4DFF),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFEDE7F6),
        secondary = Color(0xFFFF6D00),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFFFE0B2),
        background = Color(0xFFF3E5F5),
        onBackground = Color(0xFF1C1B1F),
        surface = Color.White,
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFEDE7F6),
        onSurfaceVariant = Color(0xFF444746),
        error = Color(0xFFD32F2F),
        accent = Color(0xFFFF6D00),
        previewTopBarColor = Color(0xFF7C4DFF),
        previewCardColor = Color.White,
        previewNavColor = Color(0xFFEDE7F6)
    )

    // ═══════════════════════════════════════════════════════════════
    // 6. SUNSET ORANGE — Vibrant, energetic
    // ═══════════════════════════════════════════════════════════════
    val SunsetOrange = KhataThemePreset(
        id = "sunset_orange",
        nameResKey = "theme_sunset_orange",
        primary = Color(0xFFFF6D00),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFFFE0B2),
        secondary = Color(0xFFD32F2F),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFFFCDD2),
        background = Color(0xFFFFF3E0),
        onBackground = Color(0xFF1C1B1F),
        surface = Color.White,
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFFFE0B2),
        onSurfaceVariant = Color(0xFF444746),
        error = Color(0xFFD32F2F),
        accent = Color(0xFFD32F2F),
        previewTopBarColor = Color(0xFFFF6D00),
        previewCardColor = Color.White,
        previewNavColor = Color(0xFFFFE0B2)
    )

    // ═══════════════════════════════════════════════════════════════
    // 7. MIDNIGHT — Dark, elegant
    // ═══════════════════════════════════════════════════════════════
    val Midnight = KhataThemePreset(
        id = "midnight",
        nameResKey = "theme_midnight",
        primary = Color(0xFF1A1A2E),
        onPrimary = Color.White,
        primaryContainer = Color(0xFF16213E),
        secondary = Color(0xFFE94560),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFF2C2C54),
        background = Color(0xFF0F0F23),
        onBackground = Color(0xFFE0E0E0),
        surface = Color(0xFF1A1A2E),
        onSurface = Color(0xFFE0E0E0),
        surfaceVariant = Color(0xFF16213E),
        onSurfaceVariant = Color(0xFFB0B0B0),
        error = Color(0xFFE94560),
        accent = Color(0xFFE94560),
        isDarkTheme = true,
        previewTopBarColor = Color(0xFF1A1A2E),
        previewCardColor = Color(0xFF16213E),
        previewNavColor = Color(0xFF16213E)
    )

    // ═══════════════════════════════════════════════════════════════
    // 8. PAPER WHITE — Clean, minimal
    // ═══════════════════════════════════════════════════════════════
    val PaperWhite = KhataThemePreset(
        id = "paper_white",
        nameResKey = "theme_paper_white",
        primary = Color(0xFF37474F),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFCFD8DC),
        secondary = Color(0xFFFFB300),
        onSecondary = Color(0xFF1C1B1F),
        secondaryContainer = Color(0xFFFFECB3),
        background = Color(0xFFFAFAFA),
        onBackground = Color(0xFF1C1B1F),
        surface = Color.White,
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFF5F5F5),
        onSurfaceVariant = Color(0xFF444746),
        error = Color(0xFFD32F2F),
        accent = Color(0xFFFFB300),
        previewTopBarColor = Color(0xFF37474F),
        previewCardColor = Color.White,
        previewNavColor = Color(0xFFF5F5F5)
    )


    // ═══════════════════════════════════════════════════════════════
    // ALL PRESETS — Ordered list for grid display
    // ═══════════════════════════════════════════════════════════════

    val allPresets: List<KhataThemePreset> = listOf(
        TrustBlue,
        GrowthTeal,
        FreshGreen,
        WarmRose,
        RoyalPurple,
        SunsetOrange,
        Midnight,
        PaperWhite
    )

    /**
     * Get preset by ID.
     */
    fun getById(id: String): KhataThemePreset =
        allPresets.firstOrNull { it.id == id } ?: TrustBlue


    // ═══════════════════════════════════════════════════════════════
    // ACCENT COLORS — Custom accent color options
    // ═══════════════════════════════════════════════════════════════

    data class AccentColor(
        val nameResKey: String,
        val color: Color,
        val onColor: Color = Color.White
    )

    val accentColors: List<AccentColor> = listOf(
        AccentColor("accent_blue", Color(0xFF1A73E8)),
        AccentColor("accent_green", Color(0xFF4CAF50)),
        AccentColor("accent_teal", Color(0xFF00BFA5)),
        AccentColor("accent_cyan", Color(0xFF00BCD4)),
        AccentColor("accent_indigo", Color(0xFF3F51B5)),
        AccentColor("accent_purple", Color(0xFF7C4DFF)),
        AccentColor("accent_royal_blue", Color(0xFF2979FF)),
        AccentColor("accent_pink", Color(0xFFE91E63)),
        AccentColor("accent_orange", Color(0xFFFF6D00)),
        AccentColor("accent_red", Color(0xFFD32F2F)),
        AccentColor("accent_brown", Color(0xFF795548)),
        AccentColor("accent_forest", Color(0xFF2E7D32))
    )


    // ═══════════════════════════════════════════════════════════════
    // DISPLAY MODE
    // ═══════════════════════════════════════════════════════════════

    enum class DisplayMode(val key: String) {
        LIGHT("light"),
        DARK("dark"),
        SYSTEM("system")
    }
}
