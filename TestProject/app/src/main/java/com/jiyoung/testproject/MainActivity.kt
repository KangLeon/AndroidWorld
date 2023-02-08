package com.jiyoung.testproject

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.view.GravityCompat
import com.jiyoung.testproject.Service.BasicService
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
        //设置第一个icon
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(com.google.android.material.R.drawable.material_ic_calendar_black_24dp)
        }
        
        val startActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode === Activity.RESULT_OK) {
                val returnedData = it.data?.getStringExtra("data_return")
                Toast.makeText(this,"返回了数据，${returnedData}", Toast.LENGTH_SHORT).show()
            }
        }

//        //打开基金市场，隐式intent
//        button0.setOnClickListener {
//            var fundId = "001404"
//            var intent = Intent(this, FirstActivity::class.java)
//            intent.putExtra("fund_id", fundId)
//            startActivity.launch(intent)
//        }

        //创建通知channel
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)

            val impChannel = NotificationChannel("important","Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(impChannel)
        }

//        val intent = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this,0,intent,0)
//        val notification = NotificationCompat.Builder(this, "important")
//            .setContentTitle("消息标题")
//            .setContentText("消息描述")
////            .setStyle(NotificationCompat.BigTextStyle().bigText("您当前的基金收益已经满足您的预期可以准备好卖出了，---本提示不构成投资建议，请理性投资"))
//            .setWhen(System.currentTimeMillis())
//            .setSmallIcon(R.drawable.tiny)
//            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.tiny))
//            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
//            .build()

        //发送普通通知给用户
//        button0.setOnClickListener {
//            manager.notify(1, notification)
//        }

        //启动前台server
//        button0.setOnClickListener {
//            val serviceIntent = Intent(this, BasicService::class.java)
//            startService(serviceIntent)
//        }

        //配置ListView
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
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
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