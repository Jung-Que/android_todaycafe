package com.example.and_cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.and_cafe.databinding.ActivityShareBinding
import com.example.and_cafe.MainActivity
import com.example.and_cafe.databinding.ActivityPhotoBinding


class ShareActivity : AppCompatActivity() {
    lateinit var binding: ActivityShareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_share)
        binding = ActivityShareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backShare.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, 0)
            }
            startActivity(Intent.createChooser(intent, "공유하기"))
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}