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

    // Создание ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_additionals_item, parent, false)
        return SimpleViewHolder(view)
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // Возвращает размер списка
    override fun getItemCount(): Int = items.size

    // ViewHolder для RecyclerView
    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListAdditionalsItemBinding.bind(itemView)

        fun bind(item: Meal) {
            binding.tvMealTitle.text = item.name

            Glide.with(itemView)
                .load(item.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_cover_placeholder)
                .into(binding.ivMealPicture)
        }
    }
}
