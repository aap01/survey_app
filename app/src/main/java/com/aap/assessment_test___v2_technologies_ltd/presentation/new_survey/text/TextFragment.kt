package com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.text

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.QuestionFragment
import kotlinx.android.synthetic.main.fragment_text.*
import kotlinx.android.synthetic.main.question.*

class TextFragment: QuestionFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_text

    override fun isValid(): Boolean {
        var isValid = true
        if (question.isRequired) {
            if (TextUtils.isEmpty(userInput.text?.trim())) {
                isValid = false
                userInput.error = "Require field"
            }
        }
        return isValid
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionStatement.text = getQuestionTitleWithConstraint()
        userInput.setText(question.answerFromKeyboard)
        userInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                question.answerFromKeyboard = s.toString()
            }
        })
    }
}