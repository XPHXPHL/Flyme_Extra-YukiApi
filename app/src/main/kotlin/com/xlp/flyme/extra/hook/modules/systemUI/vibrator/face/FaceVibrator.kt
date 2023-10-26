package com.xlp.flyme.extra.hook.modules.systemUI.vibrator.face

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.XUtils
import com.xlp.flyme.extra.utils.getBoolean
import com.xlp.flyme.extra.utils.getInt

object FaceVibrator : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("vibrator_face", false)) {
            "com.flyme.systemui.facerecognition.FaceRecognitionAnimationView".toClass()
                .method {
                    name = "startSuccessAnimation"
                }.hook {
                    after {
                        val effectId = getInt("vibrator_effect_id_face", 31021)
                        XUtils.vibratorHelper(effectId)
                        YLog.debug(tag = tag, msg = "Face vibrator effectID is $effectId")
                    }
                }.result {
                    onAllFailure {
                        YLog.error(tag = tag, e = it)
                    }
                }
        }
    }
}