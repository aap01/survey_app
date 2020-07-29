package com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.checkbox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Option
import kotlinx.android.synthetic.main.item_checkbox.view.*
import kotlinx.android.synthetic.main.item_mcq.view.option

class CheckboxAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemList: List<Option> = emptyList()
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return CheckboxViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_checkbox, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as CheckboxViewHolder
        val item = itemList[position]
        holder.apply {
            option.text = item.option
            cb.isChecked = item.isSelected
            itemView.setOnClickListener {
                item.isSelected = !item.isSelected
                notifyItemChanged(position)
            }
        }
    }

    class CheckboxViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val option: TextView = itemView.option
        val cb: CheckBox = itemView.cb
    }

    fun setItemList(itemList: List<Option>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }
}