package com.aap.assessment_test___v2_technologies_ltd.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.domain.database.AppDatabase
import com.aap.assessment_test___v2_technologies_ltd.domain.database.SurveyDao
import com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.NewSurveyFragment
import com.aap.assessment_test___v2_technologies_ltd.presentation.previous_survey.PreviousSurveyFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), INewSurveyClick, IFinishSurvey {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchPreviousSurveyFragment()
    }


    private fun debugDB() {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            val surveyDao: SurveyDao by inject()
            surveyDao.getPreviousSurveys().map {
                Log.d("AAP", it.toString())
            }
            surveyDao.getAllQs().map {
                Log.d("AAP", it.toString())
            }
        }
    }

    private fun launchPreviousSurveyFragment() {
        val previousSurveyFragment = PreviousSurveyFragment()
        previousSurveyFragment.iNewSurveyClick = this
        supportFragmentManager
            .beginTransaction()
            .replace(container.id, previousSurveyFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun launchNewSurveyFragment() {
        val newSurveyFragment = NewSurveyFragment()
        newSurveyFragment.iFinishSurvey = this
        supportFragmentManager
            .beginTransaction()
            .replace(container.id, newSurveyFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) finish()
        else supportFragmentManager.popBackStack()
    }

    override fun onClick() {
        launchNewSurveyFragment()
    }

    override fun onFinished() {
        onBackPressed()
    }
}