package com.jiyoung.testproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiyoung.testproject.model.FundAllModel
import com.jiyoung.testproject.model.FundSearchAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val fundData = ArrayList<FundAllModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        bindUI()
    }

    private fun bindUI() {
        search_bar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                Toast.makeText(this@SearchActivity,"搜索内容改变，${p0}", Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                Toast.makeText(this@SearchActivity,"点击搜索按钮，${p0}", Toast.LENGTH_SHORT).show()
                return false
            }
        })

        var extraData = intent.getStringExtra("fund_id")
        Toast.makeText(this,"传入了数据，${extraData}", Toast.LENGTH_SHORT).show()

        fundData.add(FundAllModel("001404","招商移动互联网","招商移动互联网","股票型","招商移动互联网"))
        fundData.add(FundAllModel("320007","诺安成长混合","诺安成长混合","混合偏股型","诺安成长混合"))

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = FundSearchAdapter(fundData)
        recyclerView.adapter = adapter
    }

    fun backData() {
        val intent = Intent()
        intent.putExtra("data_return", "Hello FirstActivity")
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onBackPressed() {
        backData()
    }
}