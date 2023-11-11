package com.xlp.flyme.extra.activity.pages

import android.view.View
import android.widget.Toast
import cn.fkj233.ui.activity.MIUIActivity.Companion.safeSP
import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import cn.fkj233.ui.dialog.MIUIDialog
import com.xlp.flyme.extra.R
import com.xlp.flyme.extra.utils.XUtils.perfVibratorHelper

@BMPage("VibratorPage", hideMenu = false)
class VibratorPage : BasePage() {
    override fun getTitle(): String {
        setTitle(getString(R.string.vibrator_page))
        return getString(R.string.vibrator_page)
    }

    private fun viewToInput(
        perfKey: String,
        effectKey: String,
        textSummaryWithSwitchTextId: Int,
        textSummaryWithSwitchTipsId: Int,
        textSummaryVTextId: Int,
        textSummaryVTipsId: Int,
        setTitle: Int,
    ) {
        val animRatioBinding =
            GetDataBinding({ safeSP.getBoolean(perfKey, false) }) { view, flags, data ->
                if (flags == 1) view.visibility = if (data as Boolean) View.VISIBLE else View.GONE
            }
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = textSummaryWithSwitchTextId,
                tipsId = textSummaryWithSwitchTipsId
            ),
            SwitchV(perfKey, onClickListener = { animRatioBinding.send(it) })
        )
        TextSummaryWithArrow(
            TextSummaryV(
                textId = textSummaryVTextId,
                tipsId = textSummaryVTipsId,
                onClickListener = {
                    MIUIDialog(activity) {
                        setTitle(setTitle)
                        setMessage(
                            "${activity.getString(R.string.def)}31021，${activity.getString(R.string.current)}${
                                safeSP.getInt(effectKey, 31021)
                            }"
                        )
                        setEditText("", "")
                        setLButton(textId = R.string.cancel) {
                            dismiss()
                        }
                        setRButton(textId = R.string.done) {
                            if (getEditText().isNotEmpty()) {
                                runCatching {
                                    safeSP.putAny(
                                        effectKey,
                                        getEditText().toInt()
                                    )
                                }.onFailure {
                                    Toast.makeText(
                                        activity,
                                        R.string.warn,
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                            dismiss()
                        }
                        dismiss()
                    }.show()
                }
            ), dataBindingRecv = animRatioBinding.getRecv(1))
    }

    override fun onCreate() {
        viewToInput(
            perfKey = "vibrator_back",
            effectKey = "vibrator_effect_id_back",
            textSummaryWithSwitchTextId = R.string.vibrator_back,
            textSummaryWithSwitchTipsId = R.string.vibrator_back_summary,
            textSummaryVTextId = R.string.vibrator_effect_back,
            textSummaryVTipsId = R.string.vibrator_effect_back,
            setTitle = R.string.vibrator_effect_back
        )
        viewToInput(
            perfKey = "vibrator_face",
            effectKey = "vibrator_effect_id_face",
            textSummaryWithSwitchTextId = R.string.vibrator_face,
            textSummaryWithSwitchTipsId = R.string.vibrator_face_summary,
            textSummaryVTextId = R.string.vibrator_effect_face,
            textSummaryVTipsId = R.string.vibrator_effect_face,
            setTitle = R.string.vibrator_effect_face
        )
        viewToInput(
            perfKey = "vibrator_fingerprint",
            effectKey = "vibrator_effect_id_fingerprint",
            textSummaryWithSwitchTextId = R.string.vibrator_fingerprint,
            textSummaryWithSwitchTipsId = R.string.vibrator_fingerprint_summary,
            textSummaryVTextId = R.string.vibrator_effect_fingerprint,
            textSummaryVTipsId = R.string.vibrator_effect_fingerprint,
            setTitle = R.string.vibrator_effect_fingerprint
        )
        Line()
        TextSummaryWithArrow(
            TextSummaryV(
                textId = R.string.vibrator_effect,
                tipsId = R.string.vibrator_effect,
                onClickListener = {
                    MIUIDialog(activity) {
                        setTitle(R.string.vibrator_effect)
                        setLButton(textId = R.string.cancel) {
                            dismiss()
                        }
                        setEditText("", "")
                        setRButton(textId = R.string.done) {
                            if (getEditText().isNotEmpty()) {
                                runCatching {
                                    safeSP.putAny(
                                        "vibrator_effect_id",
                                        getEditText().toInt()
                                    )
                                }.onFailure {
                                    Toast.makeText(
                                        activity,
                                        R.string.warn,
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                            val effectId = safeSP.getInt("vibrator_effect_id", 0)
                            perfVibratorHelper(effectId)
                        }
                        dismiss()
                    }.show()
                })
        )
    }
}