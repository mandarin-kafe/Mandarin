package com.mandarinkafe.mandarin.ui.meal_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.ListAdditionalsItemBinding

import com.mandarinkafe.mandarin.domain.models.Meal
import java.util.ArrayList

class MealDetailsAdapter(private val items: ArrayList<Meal>) :
    RecyclerView.Adapter<MealDetailsAdapter.SimpleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_additionals_item, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bind(items[position])
    }


    override fun getItemCount(): Int = items.size


    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListAdditionalsItemBinding.bind(itemView)

        fun bind(item: Meal) {
            binding.apply {
                tvMealTitle.text = item.name + ", " + item.weight.toString() + " г"
                tvMealPrice.text = item.price.toString() + " ₽"

                Glide.with(itemView)
                    .load(item.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_cover_placeholder)
                    .into(ivMealPicture)
            }
        }
    }
}
