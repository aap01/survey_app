package com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.dropdown

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.QuestionFragment
import kotlinx.android.synthetic.main.fragment_dropdown.*
import kotlinx.android.synthetic.main.question.*


class DropdownFragment : QuestionFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_dropdown

    override fun isValid(): Boolean {
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionStatement.text = getQuestionTitleWithConstraint()
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, question.options.map { it.option })
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                question.options.map { it.isSelected = false }
                question.options[position].isSelected = true
            }
        }
        try {
            question.options.first { it.isSelected }?.let {
                val index = adapter.getPosition(it.option)
                spinner.setSelection(index)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}