package com.xlp.flyme.extra.hook.modules.systemUI.appShade

import android.os.IBinder
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.getBoolean
import de.robv.android.xposed.XposedHelpers

object AppShade : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("app_shade", false)) {
            "com.android.wm.shell.startingsurface.StartingWindowController".toClass()
                .method {
                    name =
                        "lambda\$addStartingWindow$0\$com-android-wm-shell-startingsurface-StartingWindowController"
                    param("android.window.StartingWindowInfo", IBinder::class.java)
                }.hook {
                    before {
                        val windowInfo = this.args[0]
                        val appToken = this.args[1] as IBinder
                        val mStartingSurfaceDrawer =
                            XposedHelpers.getObjectField(this.instance, "mStartingSurfaceDrawer")
                        XposedHelpers.callMethod(
                            mStartingSurfaceDrawer,
                            "addSplashScreenStartingWindow",
                            windowInfo,
                            appToken,
                            0
                        )
                    }
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }
    }
}