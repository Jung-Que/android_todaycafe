package com.example.and_cafe

import android.app.Activity
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.and_cafe.databinding.CardViewBinding



class MyViewHolder(val binding: CardViewBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter (val contents1: MutableList<Int>?, val contents2: MutableList<String>?, val contents3: MutableList<String>?,val clickListener: (Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    //private lateinit var itemClickListener: View.OnClickListener
    fun setItemClickListener(position: Int) : Int {
        return position
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(CardViewBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val binding = (holder as MyViewHolder).binding
        binding.imageView.setImageResource(contents1!![position])
        binding.titleText.text = (contents2!![position])
        binding.detailText.text = (contents3!![position])

        binding.root.setOnClickListener {
            clickListener(position)
            Log.d("jj","${contents1[position]} , $position")
        }
    }

    override fun getItemCount(): Int {
        return contents1?.size ?: 0
    }

//    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        var imageItem : ImageView
//        var tittleItem : TextView
//        var ditealItem :TextView
//        init {
//            imageItem = itemView.findViewById(R.id.imageView)
//            tittleItem = itemView.findViewById(R.id.titleText)
//            ditealItem = itemView.findViewById(R.id.detailText)
//
//            itemView.setOnClickListener{
//                itemView.tag = this
//                Log.d("jj","c")
//                itemView.setOnClickListener(itemClickListener)
//            }
//
//        }
//    }

}

//class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){
//    override fun getItemOffsets(
//        outRect: Rect,
//        view: View,
//        parent: RecyclerView,
//        state: RecyclerView.State
//    ) {
//        super.getItemOffsets(outRect, view, parent, state)
//        val index = parent.getChildAdapterPosition(view) + 1
//        if(index % 3 == 0)
//            outRect.set(10,10,10,65)
//        else
//            outRect.set(10,10,10,0)
//
//        view.setBackgroundColor(Color.parseColor("#28A0FF"))
//        ViewCompat.setElevation(view, 20.0f)
//    }
//}