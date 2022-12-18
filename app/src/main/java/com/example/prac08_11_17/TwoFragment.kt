package com.example.and_cafe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.and_cafe.R
import com.example.and_cafe.databinding.FragmentOneBinding
import com.example.and_cafe.databinding.FragmentTwoBinding
import com.example.and_cafe.BookActivity

class TwoFragment : Fragment() {
    lateinit var binding: FragmentTwoBinding
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTwoBinding.inflate(layoutInflater)

        binding.ediyaBook.setOnClickListener{
            val intent = Intent(mainActivity, BookActivity::class.java)
            startActivity(intent)

        }

        return binding.root
    }
}