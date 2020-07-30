package com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Question
import com.google.gson.Gson

abstract class QuestionFragment : Fragment(), IValidate {

    lateinit var question: Question

    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    fun getQuestionTitleWithConstraint(): String {
        return if (question.isRequired) question.question
        else question.question + " " + getString(R.string.optional)
    }


}