package com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.number

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.QuestionFragment
import kotlinx.android.synthetic.main.fragment_number.*
import kotlinx.android.synthetic.main.question.*

class NumberFragment: QuestionFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_number

    override fun isValid(): Boolean {
        var isValid = true
        if(question.isRequired) {
            if (TextUtils.isEmpty(number.text)) {
                isValid = false
                number.error = "Required"
            }
        }
        return isValid
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionStatement.text = getQuestionTitleWithConstraint()
        number.setText(question.answerFromKeyboard)
        number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                question.answerFromKeyboard = s.toString()
            }
        })
    }
}