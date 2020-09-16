package com.example.testimage.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testimage.R
import com.example.testimage.datamodel.Data
import kotlinx.android.synthetic.main.activity_image_adapter.view.*

class MyAdapter(private var c: Context, private var mydata: List<Data>) :
    RecyclerView.Adapter<MyAdapter.MViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_image_adapter, parent, false)
        return MViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mydata.size
    }

    override fun onBindViewHolder(holder: MyAdapter.MViewHolder, position: Int) {
        holder.bind(mydata[position],position = position)

    }

    fun update(data: List<Data>) {
        if (data == null) {
            mydata = data
        } else {
            mydata = mydata + data
        }
        notifyDataSetChanged()
    }


    inner class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(banner: Data,position: Int) {

            if (position % 2 == 1) {
                itemView.textView4.text = "Family Sharing"
                itemView.itemBack.setBackgroundResource(R.drawable.lldesign_orange)
                itemView.cardInside.setBackgroundResource(R.drawable.carddesign)
                itemView.textView7.setBackgroundResource(R.drawable.textviewdesign1)
                itemView.view11.setBackgroundColor(Color.parseColor("#FE4D01"))

            } else {
                itemView.itemBack.setBackgroundResource(R.drawable.lldesign)
                itemView.cardInside.setBackgroundResource(R.drawable.carddesign)
                itemView.textView7.setBackgroundResource(R.drawable.textviewdesign)
                itemView.textView4.text = "Single User"
                itemView.view11.setBackgroundColor(Color.parseColor("#A9A9A9"))

            }
            itemView.textView3.text = banner.service_name

        }
    }
}