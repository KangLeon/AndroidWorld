package com.jiyoung.testproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindUI()
    }

    fun bindUI() {
        button0.setOnClickListener {
            //显示intent
//            var intent = Intent(this, FirstActivity::class.java)
//            startActivity(intent)
            //隐式intent
            var intent = Intent("com.jiyoung.testobject.ACTION_START")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "添加基金", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "编辑基金", Toast.LENGTH_SHORT).show()
        }

        return  true
    }
}