package com.xlp.flyme.extra.hook.modules.systemUI.vibrator.face

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.XUtils

object FaceVibrator : YukiBaseHooker() {
    override fun onHook() {
        "com.flyme.systemui.facerecognition.FaceRecognitionAnimationView".toClass()
            .method {
                name = "startSuccessAnimation"
            }.hook {
                after {
                    XUtils.vibratorHelper(31021)
                }
            }.result {
                onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
            }
    }
}