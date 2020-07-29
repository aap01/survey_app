package com.aap.assessment_test___v2_technologies_ltd.presentation.previous_survey

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.data.util.ErrorResponse
import com.aap.assessment_test___v2_technologies_ltd.data.util.Loading
import com.aap.assessment_test___v2_technologies_ltd.data.util.ModelResponse
import com.aap.assessment_test___v2_technologies_ltd.data.util.SuccessResponse
import com.aap.assessment_test___v2_technologies_ltd.domain.usecase.GetPreviousSurveyUC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PreviousSurveyVM(
    private val getPreviousSurveyUC: GetPreviousSurveyUC,
    override val coroutineContext: CoroutineContext
) : ViewModel(), CoroutineScope {

    private val _previousSurveyData = MutableLiveData<ModelResponse<List<Survey>>> ()
    val prevSurveyData: LiveData<ModelResponse<List<Survey>>> = _previousSurveyData

    fun get() {
        launch {
            _previousSurveyData.postValue(Loading())
            try {
                _previousSurveyData.postValue(SuccessResponse(body = getPreviousSurveyUC.get()))
            } catch (e: Exception) {
                _previousSurveyData.postValue(ErrorResponse(errorMessage = e.message))
            }
        }
    }
}