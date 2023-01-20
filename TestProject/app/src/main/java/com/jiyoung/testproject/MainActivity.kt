package com.jiyoung.testproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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
            //显示intent
//            var intent = Intent(this, FirstActivity::class.java)
//            startActivity(intent)

            //隐式intent
            var fundId = "001404"
            var intent = Intent(this, FirstActivity::class.java)
            intent.putExtra("fund_id", fundId)
            startActivity.launch(intent)
        }
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