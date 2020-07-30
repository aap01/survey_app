package com.aap.assessment_test___v2_technologies_ltd.presentation.extensions

import android.text.format.DateUtils
import java.util.*

fun Long.toAgoString(): String = DateUtils.getRelativeTimeSpanString(this).toString()

