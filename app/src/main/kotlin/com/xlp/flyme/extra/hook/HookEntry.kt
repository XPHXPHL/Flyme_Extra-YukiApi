package com.xlp.flyme.extra.hook

import android.annotation.SuppressLint
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import com.xlp.flyme.extra.BuildConfig
import com.xlp.flyme.extra.hook.modules.android.forcedScreenCapture.ForcedScreenCapture
import com.xlp.flyme.extra.hook.modules.android.gameFps.LockGameFps
import com.xlp.flyme.extra.hook.modules.systemUI.appShade.AppShade
import com.xlp.flyme.extra.hook.modules.systemUI.blur.SupportBlur
import com.xlp.flyme.extra.hook.modules.systemUI.vibrator.back.BackVibrator
import com.xlp.flyme.extra.hook.modules.systemUI.vibrator.face.FaceVibrator
import com.xlp.flyme.extra.hook.modules.systemUI.vibrator.fingerprint.FingerprintVibrator
import com.xlp.flyme.extra.hook.modules.systemUIex.forcedScreenCapture.ForcedScreenCapture2

@InjectYukiHookWithXposed
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            tag = "XLP_DEBUG"
            isRecord = true
            isDebug = BuildConfig.DEBUG
            elements(TAG, PRIORITY, PACKAGE_NAME, USER_ID)
        }
    }

    @SuppressLint("SdCardPath")
    override fun onHook() = encase {
        loadApp(name = "com.android.systemui") {
            loadHooker(SupportBlur)
            loadHooker(BackVibrator)
            loadHooker(FaceVibrator)
            loadHooker(FingerprintVibrator)
            loadHooker(AppShade)
        }
        loadApp(name = "com.flyme.systemuiex") {
            loadHooker(ForcedScreenCapture2)
        }
        loadApp(name = "android") {
            loadHooker(LockGameFps)
            loadHooker(ForcedScreenCapture)
        }
        YLog.saveToFile("/sdcard/Documents/YukiApi-Flyme_Extra-Debug_Log.log")
    }

}