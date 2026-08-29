package com.khatabook.app.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ═══════════════════════════════════════════════════════════════════
 * THEME SELECTION VIEW MODEL — Manages theme selection state
 * ═══════════════════════════════════════════════════════════════════
 *
 * State flow:
 *   User selects theme → UI updates preview → User taps Apply → Save to preferences
 *
 * Supports:
 *   - 8 theme presets
 *   - 12 custom accent colors
 *   - Light / Dark / System display modes
 *   - Live preview before applying
 */
class ThemeSelectionViewModel : ViewModel() {

    // ═══════════════════════════════════════════════════════════════
    // UI STATE
    // ═══════════════════════════════════════════════════════════════

    data class ThemeSelectionState(
        val selectedPresetId: String = "trust_blue",
        val selectedAccentIndex: Int = 0,
        val displayMode: KhataThemePresets.DisplayMode = KhataThemePresets.DisplayMode.SYSTEM,
        val hasUnsavedChanges: Boolean = false,
        val isApplied: Boolean = false,
        val language: String = "en"  // en, ur, ur-roman
    ) {
        val selectedPreset: KhataThemePreset
            get() = KhataThemePresets.getById(selectedPresetId)

        val selectedAccent: KhataThemePresets.AccentColor
            get() = KhataThemePresets.accentColors.getOrElse(selectedAccentIndex) {
                KhataThemePresets.accentColors.first()
            }

        /**
         * Preview theme with custom accent color applied.
         */
        val previewTheme: KhataThemePreset
            get() = selectedPreset.copy(
                accent = selectedAccent.color
            )
    }

    private val _state = MutableStateFlow(ThemeSelectionState())
    val state: StateFlow<ThemeSelectionState> = _state.asStateFlow()


    // ═══════════════════════════════════════════════════════════════
    // ACTIONS
    // ═══════════════════════════════════════════════════════════════

    /**
     * User taps a theme preset card.
     */
    fun selectPreset(presetId: String) {
        _state.update { currentState ->
            currentState.copy(
                selectedPresetId = presetId,
                hasUnsavedChanges = currentState.selectedPresetId != presetId ||
                        currentState.selectedAccentIndex != currentState.selectedAccentIndex,
                isApplied = false
            )
        }
        markUnsaved()
    }

    /**
     * User taps an accent color circle.
     */
    fun selectAccent(index: Int) {
        _state.update { it.copy(selectedAccentIndex = index, isApplied = false) }
        markUnsaved()
    }

    /**
     * User toggles display mode.
     */
    fun selectDisplayMode(mode: KhataThemePresets.DisplayMode) {
        _state.update { it.copy(displayMode = mode, isApplied = false) }
        markUnsaved()
    }

    /**
     * User taps Apply button.
     */
    fun applyTheme() {
        viewModelScope.launch {
            // In real app: save to DataStore/SharedPreferences
            // For now, just mark as applied
            _state.update { it.copy(hasUnsavedChanges = false, isApplied = true) }
        }
    }

    /**
     * Reset to default theme.
     */
    fun resetToDefault() {
        _state.update {
            ThemeSelectionState(
                selectedPresetId = "trust_blue",
                selectedAccentIndex = 0,
                displayMode = KhataThemePresets.DisplayMode.SYSTEM,
                hasUnsavedChanges = true,
                isApplied = false,
                language = it.language
            )
        }
    }

    /**
     * Set language for the screen.
     */
    fun setLanguage(lang: String) {
        _state.update { it.copy(language = lang) }
    }

    /**
     * Load saved theme preferences.
     * Called on screen entry.
     */
    fun loadSavedTheme(
        presetId: String = "trust_blue",
        accentIndex: Int = 0,
        displayMode: KhataThemePresets.DisplayMode = KhataThemePresets.DisplayMode.SYSTEM,
        lang: String = "en"
    ) {
        _state.update {
            ThemeSelectionState(
                selectedPresetId = presetId,
                selectedAccentIndex = accentIndex,
                displayMode = displayMode,
                language = lang
            )
        }
    }


    // ═══════════════════════════════════════════════════════════════
    // PRIVATE
    // ═══════════════════════════════════════════════════════════════

    private fun markUnsaved() {
        _state.update { it.copy(hasUnsavedChanges = true) }
    }
}
