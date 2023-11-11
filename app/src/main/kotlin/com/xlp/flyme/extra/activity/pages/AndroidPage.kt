package com.xlp.flyme.extra.activity.pages

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.xlp.flyme.extra.R

@BMPage("AndroidPage", hideMenu = false)
class AndroidPage:BasePage() {
    override fun getTitle(): String {
        setTitle(getString(R.string.android_page))
        return getString(R.string.android_page)
    }
    override fun onCreate() {
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.forced_screen_capture, tipsId = R.string.forced_screen_capture_summary),
            SwitchV("forced_screen_capture")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.unlock_game_fps, tipsId = R.string.unlock_game_fps_summary),
            SwitchV("unlock_game_fps")
        )
    }
}