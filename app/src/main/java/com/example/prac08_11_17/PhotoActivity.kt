package com.example.and_cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.and_cafe.databinding.ActivityPhotoBinding
import com.example.and_cafe.MainActivity
import com.example.and_cafe.databinding.ActivityLoadingBinding


class PhotoActivity : AppCompatActivity() {
    lateinit var binding: ActivityPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_photo)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}