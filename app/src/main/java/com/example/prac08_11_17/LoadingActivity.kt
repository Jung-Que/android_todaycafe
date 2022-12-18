package com.example.and_cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.airbnb.lottie.LottieAnimationView
import com.example.and_cafe.databinding.ActivityLoadingBinding

import com.example.and_cafe.MainActivity

import kotlin.random.Random

class LoadingActivity : AppCompatActivity() {
    lateinit var  binding : ActivityLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
            setContentView(binding.root)

        val splashImage = binding.splashImage as LottieAnimationView
        splashImage.playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },1000L+ Random.nextLong(2000))
    }
}