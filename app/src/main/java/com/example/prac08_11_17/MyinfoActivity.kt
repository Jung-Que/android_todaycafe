package com.example.and_cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat.startActivity
import com.example.and_cafe.databinding.ActivityMyinfoBinding


class MyinfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyinfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_myinfo)
        binding = ActivityMyinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == R.id.){
//            Intent(this, SettingActivity::class.java)
//            startActivity(intent)
//        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}