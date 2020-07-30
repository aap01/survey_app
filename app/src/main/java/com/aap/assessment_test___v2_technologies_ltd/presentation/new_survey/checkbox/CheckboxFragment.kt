package com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.checkbox

import android.os.Bundle
import android.widget.Toast
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.QuestionFragment
import kotlinx.android.synthetic.main.fragment_checkbox.*
import kotlinx.android.synthetic.main.question.*
import org.koin.android.ext.android.inject

class CheckboxFragment: QuestionFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_checkbox

    override fun isValid(): Boolean {
        var isValid = true
        if (question.isRequired) {
            isValid = question.options.any { it.isSelected }
            if (!isValid) Toast.makeText(context, "Required Question", Toast.LENGTH_SHORT).show()
        }
        return isValid
    }


    private val checkboxAdapter: CheckboxAdapter by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionStatement.text = getQuestionTitleWithConstraint()
        rvCheckbox.adapter = checkboxAdapter
        checkboxAdapter.setItemList(question.options)
    }
}