package com.aap.assessment_test___v2_technologies_ltd.presentation.previous_survey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.data.util.ErrorResponse
import com.aap.assessment_test___v2_technologies_ltd.data.util.Loading
import com.aap.assessment_test___v2_technologies_ltd.data.util.SuccessResponse
import com.aap.assessment_test___v2_technologies_ltd.presentation.INewSurveyClick
import com.aap.assessment_test___v2_technologies_ltd.presentation.extentions.gone
import com.aap.assessment_test___v2_technologies_ltd.presentation.extentions.visible
import kotlinx.android.synthetic.main.fragment_previous_survey.*
import kotlinx.android.synthetic.main.loading.*
import org.koin.android.ext.android.inject

class PreviousSurveyFragment : Fragment() {

    var iNewSurveyClick: INewSurveyClick? = null
    private val previousSurveyVM: PreviousSurveyVM by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_survey, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
        setButtonActions()
        previousSurveyVM.get()
    }

    private fun setButtonActions() {
        fab.setOnClickListener {
            iNewSurveyClick?.onClick()
        }
    }

    private fun initObservers() {
        previousSurveyVM.prevSurveyData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Loading -> loading.visible()
                is SuccessResponse -> {
                    loading.gone()
                    showSuccess(it)
                }
                is ErrorResponse -> {
                    loading.gone()
                    showErrorDialog(it)
                }
            }
        })
    }

    private fun showErrorDialog(it: ErrorResponse<List<Survey>>) {

    }

    private fun showSuccess(it: SuccessResponse<List<Survey>>) {

    }

}