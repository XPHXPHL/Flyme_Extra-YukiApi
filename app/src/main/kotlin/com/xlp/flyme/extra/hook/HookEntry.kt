package com.xlp.flyme.extra.hook

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import com.xlp.flyme.extra.BuildConfig
import com.xlp.flyme.extra.hook.modules.systemUI.blur.SupportBlur
import com.xlp.flyme.extra.hook.modules.systemUI.vibrator.back.BackVibrator
import com.xlp.flyme.extra.hook.modules.systemUI.vibrator.face.FaceVibrator
import com.xlp.flyme.extra.hook.modules.systemUI.vibrator.fingerprint.FingerprintVibrator

@InjectYukiHookWithXposed
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            tag = "XLP_DEBUG"
            isDebug = BuildConfig.DEBUG
        }
    }

    override fun onHook() = encase {
        loadApp(
            name = "com.android.systemui",
            SupportBlur,
            BackVibrator,
            FaceVibrator,
            FingerprintVibrator
        )

    }
}