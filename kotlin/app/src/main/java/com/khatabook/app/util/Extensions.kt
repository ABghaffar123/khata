package com.khatabook.app.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

// ═══════════════════════════════════════════════════════════
// CURRENCY FORMATTING
// ═══════════════════════════════════════════════════════════

/**
 * Format amount as PKR currency string.
 * Example: 5000.0 → "Rs 5,000"
 */
fun Double.toCurrency(): String {
    val format = NumberFormat.getNumberInstance(Locale("en", "PK"))
    format.maximumFractionDigits = 0
    return "${Constants.CURRENCY_SYMBOL} ${format.format(this)}"
}

/**
 * Format amount with decimals.
 * Example: 5000.50 → "Rs 5,000.50"
 */
fun Double.toCurrencyWithDecimals(): String {
    val format = NumberFormat.getNumberInstance(Locale("en", "PK"))
    format.maximumFractionDigits = 2
    return "${Constants.CURRENCY_SYMBOL} ${format.format(this)}"
}

// ═══════════════════════════════════════════════════════════
// DATE FORMATTING
// ═══════════════════════════════════════════════════════════

/**
 * Format timestamp to display date.
 * Example: timestamp → "28 Aug 2025"
 */
fun Long.toDisplayDate(): String {
    val sdf = SimpleDateFormat(Constants.DATE_FORMAT_DISPLAY, Locale.getDefault())
    return sdf.format(Date(this))
}

/**
 * Format timestamp to full date.
 * Example: timestamp → "28 August 2025"
 */
fun Long.toFullDate(): String {
    val sdf = SimpleDateFormat(Constants.DATE_FORMAT_FULL, Locale.getDefault())
    return sdf.format(Date(this))
}

/**
 * Format timestamp to short date.
 * Example: timestamp → "28/08/2025"
 */
fun Long.toShortDate(): String {
    val sdf = SimpleDateFormat(Constants.DATE_FORMAT_SHORT, Locale.getDefault())
    return sdf.format(Date(this))
}

/**
 * Get relative time string.
 * Example: timestamp → "2 hours ago", "Yesterday", "3 days ago"
 */
fun Long.toRelativeTime(): String {
    val now = System.currentTimeMillis()
    val diff = now - this

    return when {
        diff < TimeUnit.MINUTES.toMillis(1) -> "Just now"
        diff < TimeUnit.HOURS.toMillis(1) -> "${TimeUnit.MILLISECONDS.toMinutes(diff)}m ago"
        diff < TimeUnit.DAYS.toMillis(1) -> "${TimeUnit.MILLISECONDS.toHours(diff)}h ago"
        diff < TimeUnit.DAYS.toMillis(2) -> "Yesterday"
        diff < TimeUnit.DAYS.toMillis(7) -> "${TimeUnit.MILLISECONDS.toDays(diff)} days ago"
        diff < TimeUnit.DAYS.toMillis(30) -> "${TimeUnit.MILLISECONDS.toDays(diff) / 7} weeks ago"
        else -> toDisplayDate()
    }
}

/**
 * Check if timestamp is today.
 */
fun Long.isToday(): Boolean {
    val calendar = Calendar.getInstance()
    val today = calendar.apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    val tomorrow = today + TimeUnit.DAYS.toMillis(1)
    return this in today until tomorrow
}

/**
 * Get start of day for a timestamp.
 */
fun Long.startOfDay(): Long {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@startOfDay
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar.timeInMillis
}

/**
 * Get greeting based on time of day.
 */
fun getGreeting(): String {
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when {
        hour < 12 -> "Good Morning"
        hour < 17 -> "Good Afternoon"
        else -> "Good Evening"
    }
}

// ═══════════════════════════════════════════════════════════
// STRING UTILS
// ═══════════════════════════════════════════════════════════

/**
 * Get initials from name.
 * Example: "Muhammad Ali" → "MA"
 */
fun String.getInitials(): String {
    return split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .joinToString("") { it.first().uppercase() }
}

/**
 * Normalize phone number (remove dashes, spaces, etc.).
 * Example: "0300-123 4567" → "03001234567"
 */
fun String.normalizePhone(): String {
    return replace(Regex("[^\\d+]"), "")
}

/**
 * Check if string is a valid Pakistani phone number.
 */
fun String.isValidPakPhone(): Boolean {
    val normalized = normalizePhone()
    return when {
        normalized.startsWith("+92") && normalized.length == 13 -> true
        normalized.startsWith("03") && normalized.length == 11 -> true
        else -> false
    }
}

// ═══════════════════════════════════════════════════════════
// COLLECTION UTILS
// ═══════════════════════════════════════════════════════════

/**
 * Sum of a specific field in a list.
 */
inline fun <T> List<T>.sumByDouble(selector: (T) -> Double): Double {
    return fold(0.0) { acc, element -> acc + selector(element) }
}
