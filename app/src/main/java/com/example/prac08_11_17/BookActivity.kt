package com.example.and_cafe

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat.Builder

import androidx.core.app.NotificationCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.and_cafe.R
import com.example.and_cafe.databinding.ActivityBookBinding
import com.example.and_cafe.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.text.DateFormat
import java.time.LocalDate

class BookActivity : AppCompatActivity() {
    lateinit var binding:ActivityBookBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val db = openOrCreateDatabase("testdb",Context.MODE_PRIVATE,null)
        db.execSQL("create table if not exists USER_TB ("+
        "_id integer primary key autoincrement,"+
        "name not null,"+
        "phone)")
        var nowDate : LocalDate = LocalDate.now()
        var dateBook = nowDate.toString()
        var dateyear : String = "1970"
        var datemon : String = "0"
        var dateday : String = "0"
        var dayBook = false

        binding.calendarView.setOnDateChangeListener{ view, year, month, dayOfMonth ->
            dateyear = year.toString()
            datemon = (month+1).toString()
            dateday = dayOfMonth.toString()
            dateBook = ""+year+"-"+(month+1)+"-"+dayOfMonth+""

        }
        val intent = Intent(this, MainActivity::class.java)

        val eventHandler = object :DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if (p1 == DialogInterface.BUTTON_POSITIVE) {
                    val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    val builder: NotificationCompat.Builder

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channelId = "one-channel"
                        val channelName = "My Channel One"
                        val channel =
                            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW).apply {
                                description = "My Channel One Description"
                                setShowBadge(true)
                                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                                val audioAttributes = AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                    .setUsage(AudioAttributes.USAGE_ALARM)
                                    .build()
                                setSound(uri, audioAttributes)
                                enableLights(true)
                            }

                        manager.createNotificationChannel(channel)

                        builder = NotificationCompat.Builder(this@BookActivity, channelId)
                    } else {
                        builder = NotificationCompat.Builder(this@BookActivity)
                    }

                    builder.setSmallIcon(R.drawable.coffee_logo)
                    builder.setWhen(System.currentTimeMillis())
                    builder.setContentTitle("홍길동")
                    builder.setContentText("안녕하세요")
                    builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ediya))

                    builder.setAutoCancel(false)
                    builder.setOngoing(true)

                    startActivity(intent)
                } else if (p1 == DialogInterface.BUTTON_NEGATIVE) {
                    dayBook = false
                }
            }
        }
        binding.button1.setOnClickListener{
            var url = "http://10.0.2.2:8080/date"

            val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONArray> { response ->
                for(i in 0 until response.length()){
                    val jsonObject = response[i] as JSONObject
                     var year = jsonObject.getString("year")
                     var month = jsonObject.getString("month")
                     var day = jsonObject.getString("day")
                     var state = jsonObject.getString("state")

                    Log.d("jj","date $year $month $day $state")
                    Log.d("jj","date $dateyear $datemon $dateday $state")
                    if(dateyear == year && datemon == month && dateday == day){
                        Log.d("jj", "state $state")
                        if(state != "full"){
                            dayBook = true
                        }
                        else dayBook = false
                    }
                    else Log.d("jj", "cant book")
              }
            },
            Response.ErrorListener { error -> Log.d("jj","error")  })

            val queue = Volley.newRequestQueue(this)
            queue.add(jsonArrayRequest)
            if(dayBook) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("예약")
                    .setMessage("$dateBook 예약 하시겠습니까?")
                    .setPositiveButton("확인", eventHandler)
                    .setNegativeButton("취소", eventHandler)
                // 다이얼로그를 띄워주기
                builder.show()
            }
        }


        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //setContentView(R.layout.activity_book)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}