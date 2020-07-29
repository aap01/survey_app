package com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.data.util.ErrorResponse
import com.aap.assessment_test___v2_technologies_ltd.data.util.Loading
import com.aap.assessment_test___v2_technologies_ltd.data.util.ModelResponse
import com.aap.assessment_test___v2_technologies_ltd.data.util.SuccessResponse
import com.aap.assessment_test___v2_technologies_ltd.domain.usecase.FetchSurveyUC
import com.aap.assessment_test___v2_technologies_ltd.domain.usecase.StoreSurveyUC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewSurveyVM(
    private val fetchSurveyUC: FetchSurveyUC, private val storeSurveyUC: StoreSurveyUC,
    override val coroutineContext: CoroutineContext
) : ViewModel(), CoroutineScope {

    private val _newSurveyData: MutableLiveData<ModelResponse<Survey>> = MutableLiveData()
    val newSurveyData: LiveData<ModelResponse<Survey>> = _newSurveyData

    fun fetchNewSurvey() {
        launch {
            _newSurveyData.postValue(Loading())
            try {
                _newSurveyData.postValue(SuccessResponse(body = fetchSurveyUC.fetch()))
            } catch (e: Exception) {
                _newSurveyData.postValue(ErrorResponse(errorMessage = e.message))
                e.printStackTrace()
            }
        }
    }

    fun storeSurvey(survey: Survey) {
        launch {
            storeSurveyUC.store(survey)
        }
    }

}