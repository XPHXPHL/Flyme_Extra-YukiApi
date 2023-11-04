package com.xlp.flyme.extra.activity.pages

import cn.fkj233.ui.activity.annotation.BMMainPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.TextSummaryV
import com.xlp.flyme.extra.R

@BMMainPage("Flyme Extra")
class MainPage : BasePage() {
    override fun onCreate() {
        Page(
            this.getDrawable(R.drawable.ic_launcher_background),
            TextSummaryV(textId = R.string.vibrator_page),
            round = 8f,
            onClickListener = {
                showFragment("VibratorPage")
            }
        )

        Page(
            this.getDrawable(R.drawable.ic_launcher_background),
            TextSummaryV(textId = R.string.systemui_page),
            round = 8f,
            onClickListener = {
                showFragment("SystemUiPage")
            }
        )
    }
}