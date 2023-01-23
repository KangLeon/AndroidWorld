package com.jiyoung.testproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.first_layout.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        bindUI()
    }

    fun bindUI() {
        var extraData = intent.getStringExtra("fund_id")

        Toast.makeText(this,"传入了数据，${extraData}", Toast.LENGTH_SHORT).show()
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