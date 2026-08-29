package com.khatabook.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * KhataBook Application class.
 *
 * Lifecycle:
 * - Created once per process on cold start
 * - Survives config changes (Activity recreation)
 * - NOT recreated when app resumes from background
 *
 * Splash behavior:
 * - SplashActivity is the LAUNCHER with noHistory="true"
 * - Cold start: SplashActivity → MainActivity
 * - Warm resume: MainActivity resumes directly (no splash)
 */
@HiltAndroidApp
class KhataBookApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize essential components only
        // Heavy initialization (OCR, image processing) deferred to when needed
    }
}
