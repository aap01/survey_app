package com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aap.assessment_test___v2_technologies_ltd.R
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Option
import kotlinx.android.synthetic.main.item_mcq.view.*

class MCQAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemList: List<Option> = emptyList()
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return MCQViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mcq, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MCQViewHolder
        val item = itemList[position]
        holder.apply {
            option.text = item.option
            rb.isChecked = item.isSelected
            itemView.setOnClickListener {
                item.isSelected = true
                itemList
                    .filter {
                        itemList.indexOf(it) != position
                    }
                    .map {
                        it.isSelected = false
                    }
                notifyDataSetChanged()
            }
        }
    }

    class MCQViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val option: TextView = itemView.option
        val rb: RadioButton = itemView.rb
    }

    fun setItemList(itemList: List<Option>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

}