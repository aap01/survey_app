package com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey

import android.os.Bundle
import android.widget.Toast
import com.aap.assessment_test___v2_technologies_ltd.R
import kotlinx.android.synthetic.main.fragment_mcq.*
import kotlinx.android.synthetic.main.question.*
import org.koin.android.ext.android.inject

class MCQFragment: QuestionFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_mcq

    override fun isValid(): Boolean {
        var isValid = true
        if (question.isRequired) {
            isValid = question.options.any { it.isSelected }
            if (!isValid) Toast.makeText(context, "Mandatory Question", Toast.LENGTH_SHORT).show()
        }
        return isValid
    }

    private val mcqAdapter: MCQAdapter by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionStatement.text = getQuestionTitleWithConstraint()
        rvMCQ.adapter = mcqAdapter
        mcqAdapter.setItemList(question.options)
    }

}