package com.example.and_cafe

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.and_cafe.databinding.FragmentOneBinding
import com.example.and_cafe.MainActivity
import java.net.URL


class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    } //메인엑티비티 콘텍트로 바인딩
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOneBinding.inflate(layoutInflater)

        val contents1 = mutableListOf(
            R.drawable.ediyacafe,
            R.drawable.starcafe,
            R.drawable.pascucci
        )
        val contents2 = mutableListOf("Cafe 'E'","Cafe 'S'","Cafe 'P'")
        val contents3 = mutableListOf("대한민국의 커피 체인점. 순수 국내 브랜드 중 하나다.\n" +
                "\n" +
                "어감이 에티오피아스러운데, 이디야란 암하라어로 '대륙의 황제'를 뜻한다. 다만 카페명이 에티오피아 콘셉트라..."
            ,"스타벅스(Starbucks™)는 미국의 세계 최대 커피 체인점이다. 커피뿐만 아니라 차, 주스, 디저트 등을 함께 판매한다.\n" +
                    "\n" +
                    "한국에는 1999년 진출하여, 2021년 기준 1,600여개의 매장을 운영하며 커피 프랜차이즈 업계 1위를 차지..."
            , "이탈리아의 커피 브랜드.\n" +
                    "\n" +
                    "19세기 말 안토니오 파스쿠치(Antonio Pascucci)가 만들고, 마리오 파스쿠치(Mario Pascucci)가 확장해서 지금의 형태에 이르렀다고 한다.\n" +
                    "\n" +
                    "사실 이전까지는 이탈리아의 많은 커피 브랜드 중 하나였을 뿐이고, 일리, 라바짜, 세가프레도등에 비해 그다...")

        binding.recyclerview.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerview.adapter = MyAdapter(contents1,contents2,contents3){ pos->
            Log.d("jj", "$pos")
            var url: String? = "http://gun0912.tistory.com"
            var intent :Intent? = null
            var siteDomain: String = "empty"
            if(pos == 0){
                url = "https://ediya.com"
                siteDomain = "이디야"
            }else if(pos == 1){
                url = "https://www.starbucks.co.kr"
                siteDomain = "스타벅스"
            }else if(pos == 2){
                url = "https://www.starbucks.co.kr"
                siteDomain = "파스꾸찌"
            }
            val builder = AlertDialog.Builder(mainActivity)
            builder.setTitle("Go Homepage")
                .setMessage(" $siteDomain (으)로 이동합니다.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->

                    })
            // 다이얼로그를 띄워주기
            builder.show()
        }
        binding.recyclerview.addItemDecoration(
            DividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL)
        )
//        binding.recyclerview.setOnClickListener {
//
//            Log.d("jj", "clicked")
//        }




//        val contents = mutableListOf<String>()
//        for(i in 1..20)
//            contents.add("item $i")
//
//        val layoutManager = LinearLayoutManager(activity)
//        binding.recyclerview.layoutManager =  layoutManager
//        val adapter = MyAdapter(contents)
//        binding.recyclerview.adapter = adapter
//        binding.recyclerview.addItemDecoration(MyDecoration(activity as Context))
//
//        //return inflater.inflate(R.layout.fragment_one, container, false)
        return binding.root
    }

}