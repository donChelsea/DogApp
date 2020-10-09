package com.katsidzira.dogapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.katsidzira.dogapp.R
import com.katsidzira.dogapp.model.DogsApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dogs_item_view.view.*

class DogsAdapter(val context: Context, private val dogImages: List<DogsApi>) : RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dogs_item_view, parent, false)
        return DogsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogImages.size
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.onBind(dogImages[position])
    }

    class DogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val dogImage = itemView.imageview_dog

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

        fun onBind(dogsApi: DogsApi) {
            Picasso.get().load(dogsApi.message).into(dogImage)
        }

        init {
            itemView.setOnClickListener(this)
        }

    }
}