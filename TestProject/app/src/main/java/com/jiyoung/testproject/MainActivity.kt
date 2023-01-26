package com.jiyoung.testproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.jiyoung.testproject.model.FundAllAdapter
import com.jiyoung.testproject.model.FundAllModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fundData = ArrayList<FundAllModel>()
    private val fundSearchActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode === Activity.RESULT_OK) {
            val returnedData = it.data?.getStringExtra("data_return")
            Toast.makeText(this,"返回了数据，${returnedData}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindUI()
    }

    fun bindUI() {
        val startActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode === Activity.RESULT_OK) {
                val returnedData = it.data?.getStringExtra("data_return")
                Toast.makeText(this,"返回了数据，${returnedData}", Toast.LENGTH_SHORT).show()
            }
        }

        button0.setOnClickListener {
            //隐式intent
            var fundId = "001404"
            var intent = Intent(this, FirstActivity::class.java)
            intent.putExtra("fund_id", fundId)
            startActivity.launch(intent)
        }

        configListView()
    }

    fun configListView() {
        fundData.add(FundAllModel("001404","招商移动互联网","招商移动互联网","股票型","招商移动互联网"))
        fundData.add(FundAllModel("320007","诺安成长混合","诺安成长混合","混合偏股型","诺安成长混合"))
        //listView
        val adapter = FundAllAdapter(this, R.layout.fund_all_cell, fundData)
        listView.adapter = adapter

        val startActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode === Activity.RESULT_OK) {
                val returnedData = it.data?.getStringExtra("data_return")
                Toast.makeText(this,"返回了数据，${returnedData}", Toast.LENGTH_SHORT).show()
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val fundModel = fundData[position]

            var fundId = fundModel.fundCode
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("fund_id", fundId)
            startActivity.launch(intent)
        }
    }

    //open external url
    private fun openURL() {
        var intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://jiyoung.cn")
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> openAddFundPage()
            R.id.remove_item -> Toast.makeText(this, "编辑基金", Toast.LENGTH_SHORT).show()
            R.id.about_fund -> openURL()
        }

        return  true
    }

    private fun openAddFundPage() {
        var fundId = "001404"
        var intent = Intent(this, SearchActivity::class.java)
        intent.putExtra("fund_id", fundId)
        fundSearchActivity.launch(intent)
    }
}