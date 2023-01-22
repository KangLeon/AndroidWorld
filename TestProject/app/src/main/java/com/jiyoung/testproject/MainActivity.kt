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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fundData = listOf("招商移动互联网", "广发医疗A","广发医疗C", "诺安成长混合", "广发多元混合", "广发双擎")

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

        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, fundData)
        listView.adapter = adapter
    }

    fun openURL() {
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
            R.id.add_item -> Toast.makeText(this, "添加基金", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "编辑基金", Toast.LENGTH_SHORT).show()
            R.id.about_fund -> openURL()
        }

        return  true
    }
}