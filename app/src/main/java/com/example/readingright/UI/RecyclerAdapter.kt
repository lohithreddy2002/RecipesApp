package com.example.readingright.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.readingright.R
import com.example.readingright.db.Meal

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Recipieviewholder>() {


    class Recipieviewholder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffcallback = object : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this, diffcallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recipieviewholder {
        return Recipieviewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycleritem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Recipieviewholder, position: Int) {
        val Recipie = differ.currentList[position]


        holder.itemView.apply {

            Glide.with(this).load(Recipie.strMealThumb).into(findViewById(R.id.imageView))
            findViewById<TextView>(R.id.textView).text = Recipie.strMeal
            setOnClickListener {
                onItemClickListener?.let {
                    it(Recipie)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Meal) -> Unit)? = null

    fun setOnItemClickListner(listener: (Meal) -> Unit) {
        onItemClickListener = listener

    }
}