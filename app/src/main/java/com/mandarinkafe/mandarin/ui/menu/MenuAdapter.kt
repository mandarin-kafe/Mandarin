package com.mandarinkafe.mandarin.ui.menu

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.ListMenuItemBinding
import com.mandarinkafe.mandarin.domain.Meal


class MenuAdapter(private val onMealClick: (meal: Meal) -> Unit) :
    RecyclerView.Adapter<MenuAdapter.MenuHolder>() {
    private var mealList: ArrayList<Meal> = arrayListOf()
    private var isClickAllowed = true
    private val handler = Handler(Looper.getMainLooper())


    class MenuHolder(private val parentView: View) : RecyclerView.ViewHolder(parentView) {
        private val binding = ListMenuItemBinding.bind(parentView)


        fun bind(meal: Meal) = with(binding) {
            val cornerRadius =
                this@MenuHolder.parentView.resources.getDimensionPixelSize(R.dimen.image_corner_radius_2)

            tvMealTitle.text = meal.name

            if (meal.description.isNullOrEmpty()) {
                tvMealIngredients.isVisible = false
            } else {
                tvMealIngredients.text = meal.description
            }

            if (meal.weight == null) {
                tvMealWeight.isVisible = false
            } else {
                tvMealWeight.text = meal.weight.toString() + " г"
            }

            tvMealPrice.text = meal.price.toString() + " ₽"

            Glide.with(parentView)
                .load(meal.imageUrl)
                .centerCrop()
                .transform(
                    RoundedCorners(
                        cornerRadius
                    )
                )
                .placeholder(R.drawable.ic_cover_placeholder)
                .into(ivMealPicture)
            tvMealIngredients.requestLayout()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_menu_item, parent, false)
        return MenuHolder(view)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val meal = mealList[position]
        holder.bind(meal)
        holder.itemView.setOnClickListener { onClickListener(meal) }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    private fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }


    private fun onClickListener(meal: Meal) {
        if (clickDebounce()) {
            onMealClick(meal)

        }
    }

    fun submitList(mealList: ArrayList<Meal>) {
        this.mealList = mealList
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

}
