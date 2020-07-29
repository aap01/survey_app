package com.aap.assessment_test___v2_technologies_ltd.data.model.entity

import java.io.Serializable

data class Option(
    val option: String,
    var isSelected: Boolean = false
): Serializable