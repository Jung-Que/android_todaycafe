package com.example.and_cafe

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.and_cafe.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            if(position == 0) tab.text = "카페 목록"
            if(position == 1) tab.text = "좌석 예약"
            if(position == 2) tab.text = "카페 순위"
        }.attach()

        val toggle =
            ActionBarDrawerToggle(this, binding.drawer, R.string.draweropen, R.string.drawerclose)
        toggle.syncState()

        binding.mainDrawerView.setNavigationItemSelectedListener {
            var id = it.itemId;
            if(id == R.id.guide){
                Log.d("jj","guide")
                val intent = Intent(this, GuideActivity::class.java)
                startActivity(intent)
                finish()
            } else if(id == R.id.share){
                Log.d("jj","share")
                val intent = Intent(this, ShareActivity::class.java)
                startActivity(intent)
                finish()
            } else if(id == R.id.photo){
                Log.d("jj","photo")
                val intent = Intent(this, PhotoActivity::class.java)
                startActivity(intent)
                finish()
            } else if(id == R.id.myInfo){
                val intent = Intent(this, MyinfoActivity::class.java)
                startActivity(intent)
                finish()
            }
            true
        }

        binding.extFab.setOnClickListener{
            val intent = Intent(this, GuideActivity::class.java)
            startActivity(intent)
        }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
    }

    class MyFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        val fragments: List<Fragment>

        init {
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
    var pressBacktwo:Long = 0
    override fun onBackPressed() {
        if(System.currentTimeMillis() - pressBacktwo >=2000 ) {
            pressBacktwo = System.currentTimeMillis()
            Toast.makeText(this,"뒤로가기 버튼을 한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }
}