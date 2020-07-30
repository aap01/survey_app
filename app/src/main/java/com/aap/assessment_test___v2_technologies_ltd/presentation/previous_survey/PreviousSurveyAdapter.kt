package com.aap.assessment_test___v2_technologies_ltd.presentation.previous_survey

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Option
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.PreviousSurvey
import com.aap.assessment_test___v2_technologies_ltd.presentation.extensions.gone
import com.aap.assessment_test___v2_technologies_ltd.presentation.extensions.toAgoString
import com.aap.assessment_test___v2_technologies_ltd.presentation.extensions.visible
import kotlinx.android.synthetic.main.item_previous_survey.view.*

class PreviousSurveyAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: List<PreviousSurvey> = emptyList()
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return PreviousSurveyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_previous_survey, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as PreviousSurveyViewHolder
        val item = itemList[position]
        holder.apply {
            date.text = item.dateLong.toAgoString()
            totalQuestions.text = item.totalQuestions.toString()
            if (item.isExpanded) {
                rv.visible()
                expand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            }
            else {
                rv.gone()
                expand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)
            }
            rv.adapter = item.adapter
            itemView.setOnClickListener {
                item.isExpanded = !item.isExpanded
                notifyItemChanged(position)
            }
        }
    }

    class PreviousSurveyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.date
        val totalQuestions: TextView = itemView.totalQuestions
        val expand: ImageView = itemView.expand
        val rv: RecyclerView = itemView.rvItem
    }

    fun setItemList(itemList: List<PreviousSurvey>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

}