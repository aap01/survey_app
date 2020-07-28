package com.aap.assessment_test___v2_technologies_ltd.domain.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}