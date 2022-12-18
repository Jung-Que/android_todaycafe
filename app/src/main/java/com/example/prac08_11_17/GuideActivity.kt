package com.example.and_cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.and_cafe.databinding.ActivityGuideBinding
import com.example.and_cafe.MainActivity

class GuideActivity : AppCompatActivity() {
    lateinit var binding : ActivityGuideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_guide)
        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backGuide.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}