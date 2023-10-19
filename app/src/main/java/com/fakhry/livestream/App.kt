package com.fakhry.livestream

import android.app.Application
import com.bytedance.live.common.env.BDLiveConfig
import com.bytedance.live.common.env.BDLiveEnv
import com.pandora.ttlicense2.LicenseManager
import com.ss.ttvideoengine.BaseAppInfo
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initBDLiveEnv()
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initBDLiveEnv() {
        val assetsLicenseUri = "assets:///lic/example.lic"
        val assetsLicenseUri2 = "assets:///lic/example.lic"
        BDLiveEnv.init(
            BDLiveConfig.Builder()
                .setApplicationContext(this)
                .setAppId("APP_ID") // The app ID returned after the successful TTSDK license application. Contact the sales representative to obtain the AppId
                .setAppName(BaseAppInfo.mAppName) // The app name used to apply for the TTSDK license. Contact the sales representative to obtain the AppName
                .setAppChannel("channelName") // The channel through which to download the app
                .setAppVersion("M_VERSION") // The app version. A valid version number should contain two or more separators, such as 1.3.2
                .setAppRegion("ap-singapore-1") // The region used to apply for the TTSDK license. Always set the region as singapore
                .setVodLicenseUri(assetsLicenseUri) // URI of the license file for VOD
                .setLiveLicenseUri(assetsLicenseUri2) // URI of the license file for live streaming
                .setLicenseCallback(object : LicenseManager.Callback {
                    // Callbacks for license authentication results. After the authentication, SDK triggers the corresponding callback to notify you of the authentication result. You can choose whether to call the authentication result callback as needed
                    // License authentication success callback
                    override fun onLicenseLoadSuccess(licenseUri: String, licenseId: String) {}

                    // License authentication failure callback
                    override fun onLicenseLoadError(
                        licenseUri: String,
                        e: Exception,
                        retryAble: Boolean
                    ) {
                    }

                    // License authentication retry callback
                    override fun onLicenseLoadRetry(licenseUri: String) {}

                    // License update success callback
                    override fun onLicenseUpdateSuccess(licenseUri: String, licenseId: String) {}

                    // License update failure callback
                    override fun onLicenseUpdateError(
                        licenseUri: String,
                        e: Exception,
                        retryAble: Boolean
                    ) {
                    }

                    // License update retry callback
                    override fun onLicenseUpdateRetry(licenseUri: String) {}
                })
                .build()
        )
    }
}

