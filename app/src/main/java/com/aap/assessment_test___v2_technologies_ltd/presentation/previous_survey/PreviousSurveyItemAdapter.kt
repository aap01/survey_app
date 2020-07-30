package com.aap.assessment_test___v2_technologies_ltd.presentation.previous_survey

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.PreviousSurvey
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.PreviousSurveyQuestion
import kotlinx.android.synthetic.main.item_previous_question_item.view.*
import kotlinx.android.synthetic.main.item_previous_survey.view.*

class PreviousSurveyItemAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: List<PreviousSurveyQuestion> = emptyList()
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return PreviousSurveyItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_previous_question_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as PreviousSurveyItemViewHolder
        val item = itemList[position]
        holder.apply {
            question.text = item.question
            answer.text = item.answer
        }
    }

    class PreviousSurveyItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val question: TextView = itemView.question
        val answer: TextView = itemView.answer
    }

    fun setItemList(itemList: List<PreviousSurveyQuestion>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

}