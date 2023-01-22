package com.jiyoung.testproject.model

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.fund_all_cell.view.*

class FundAllModel(var fundCode :String,
                    var fundName :String,
                    var fundDisplayName :String,
                    var fundType :String,
                    var fundFullName :String)

class FundAllAdapter(activity: Activity, val resourceId: Int, data: List<FundAllModel>) : ArrayAdapter<FundAllModel>(activity, resourceId, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val fundName: TextView = view.fund_all_cell_title
        val fundType: TextView = view.fund_all_cell_type
        val fundId: TextView = view.fund_all_cell_id
        val fundAllModel = getItem(position)
        if (fundAllModel != null) {
            fundName.text = fundAllModel.fundDisplayName
            fundType.text = fundAllModel.fundType
            fundId.text = fundAllModel.fundCode
        }
        return view
    }
}