package com.khatabook.app.ui.navigation

/**
 * Navigation routes for Khata One.
 *
 * Using sealed class for type-safe navigation.
 * Each screen has a route string for NavGraph.
 */
sealed class Screen(val route: String) {

    // ═══ Main Tabs (Bottom Navigation) ═══
    data object Home : Screen("home")
    data object Customers : Screen("customers")
    data object Khata : Screen("khata")
    data object Camera : Screen("camera")
    data object Settings : Screen("settings")

    // ═══ Customer Flow ═══
    data object CustomerDetail : Screen("customer/{customerId}") {
        fun createRoute(customerId: Long) = "customer/$customerId"
    }
    data object AddCustomer : Screen("add_customer")
    data object EditCustomer : Screen("edit_customer/{customerId}") {
        fun createRoute(customerId: Long) = "edit_customer/$customerId"
    }

    // ═══ Transaction Flow ═══
    data object NewTransaction : Screen("new_transaction?customerId={customerId}") {
        fun createRoute(customerId: Long? = null) =
            if (customerId != null) "new_transaction?customerId=$customerId"
            else "new_transaction"
    }
    data object TransactionDetail : Screen("transaction/{transactionId}") {
        fun createRoute(transactionId: Long) = "transaction/$transactionId"
    }

    // ═══ Khata Register ═══
    data object KhataRegister : Screen("khata_register")
    data object KhataDateDetail : Screen("khata_date/{date}") {
        fun createRoute(date: Long) = "khata_date/$date"
    }

    // ═══ OCR Flow ═══
    data object OcrScan : Screen("ocr_scan")
    data object OcrReview : Screen("ocr_review/{captureId}") {
        fun createRoute(captureId: Long) = "ocr_review/$captureId"
    }

    // ═══ Search ═══
    data object Search : Screen("search")

    // ═══ Reports ═══
    data object Reports : Screen("reports")
    data object ReportDetail : Screen("report_detail/{reportType}") {
        fun createRoute(reportType: String) = "report_detail/$reportType"
    }

    // ═══ Settings Sub-screens ═══
    data object LanguageSettings : Screen("language_settings")
    data object ThemeSettings : Screen("theme_settings")
    data object SecuritySettings : Screen("security_settings")
    data object BackupCenter : Screen("backup_center")
    data object About : Screen("about")

    // ═══ Language Selection (First Launch) ═══
    data object LanguageSelection : Screen("language_selection")
}
