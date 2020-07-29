package com.aap.assessment_test___v2_technologies_ltd.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.NewSurveyFragment
import com.aap.assessment_test___v2_technologies_ltd.presentation.previous_survey.PreviousSurveyFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INewSurveyClick, IFinishSurvey {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchPreviousSurveyFragment()
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