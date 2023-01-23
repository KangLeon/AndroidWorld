package com.jiyoung.testproject.model

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jiyoung.testproject.R
import kotlinx.android.synthetic.main.fund_all_cell.view.*

class FundAllModel(var fundCode :String,
                    var fundName :String,
                    var fundDisplayName :String,
                    var fundType :String,
                    var fundFullName :String)

class FundAllAdapter(activity: Activity, val resourceId: Int, data: List<FundAllModel>) : ArrayAdapter<FundAllModel>(activity, resourceId, data) {
    inner class ViewHolder(val fundName: TextView, val  fundType: TextView, val fundId: TextView)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)

            val fundName: TextView = view.fund_all_cell_title
            val fundType: TextView = view.fund_all_cell_type
            val fundId: TextView = view.fund_all_cell_id
            viewHolder = ViewHolder(fundName,fundType,fundId)
            view.tag = viewHolder
        }else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val fundAllModel = getItem(position)
        if (fundAllModel != null) {
            viewHolder.fundName.text = fundAllModel.fundDisplayName
            viewHolder.fundType.text = fundAllModel.fundType
            viewHolder.fundId.text = fundAllModel.fundCode
        }
        return view
    }
}

class FundSearchAdapter(val fundArrayList: List<FundAllModel>) : RecyclerView.Adapter<FundSearchAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fundName: TextView = view.fund_all_cell_title
        val fundType: TextView = view.fund_all_cell_type
        val fundId: TextView = view.fund_all_cell_id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fund_search_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fundModel = fundArrayList[position]
        holder.fundName.text = fundModel.fundDisplayName
        holder.fundType.text = fundModel.fundType
        holder.fundId.text = fundModel.fundCode
    }

    override fun getItemCount(): Int {
        return fundArrayList.size
    }
}