package com.mandarinkafe.mandarin.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.ItemCartBinding
import com.mandarinkafe.mandarin.menu.domain.models.Item

class CartAdapter(private val items: List<Item>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemCartBinding.bind(item)

        fun bind(item: Item) = with(binding) {
            tvMealTitle.text = item.name
            tvMealIngredients.text = item.description
            tvMealWeight.text = item.weight.toString() + " г"
            cartPrice.text = item.price.toString() + " ₽"
            Glide.with(itemView.context)
                .load(item.imageUrl)
                .into(ivMealPicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}