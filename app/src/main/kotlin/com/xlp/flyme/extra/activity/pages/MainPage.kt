package com.xlp.flyme.extra.activity.pages

import cn.fkj233.ui.activity.annotation.BMMainPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.TextSummaryV
import com.xlp.flyme.extra.R

@BMMainPage("Flyme Extra")
class MainPage : BasePage() {
    override fun onCreate() {
        TextSummaryWithArrow(
            TextSummaryV(textId = R.string.android_page, tipsId = R.string.android_page_summary ,onClickListener = {
                showFragment("AndroidPage")
            }
            )
        )
        Line()
        TextSummaryWithArrow(
            TextSummaryV(textId = R.string.vibrator_page, onClickListener = {
                showFragment("VibratorPage")
            }
            )
        )
        TextSummaryWithArrow(
            TextSummaryV(textId = R.string.settings_page, onClickListener = {
                showFragment("SettingsPage")
            }
            )
        )
        TextSummaryWithArrow(
            TextSummaryV(textId = R.string.systemui_page, onClickListener = {
                showFragment("SystemUiPage")
            }
            )
        )
    }
}