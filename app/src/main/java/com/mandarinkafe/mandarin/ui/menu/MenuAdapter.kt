package com.mandarinkafe.mandarin.ui.menu

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.ListMenuItemBinding
import com.mandarinkafe.mandarin.domain.models.Meal
import com.mandarinkafe.mandarin.domain.models.MenuItem


class MenuAdapter(
    private val items: List<MenuItem>,
    private val clickListener: MealClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isClickAllowed = true
    private val handler = Handler(Looper.getMainLooper())


    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MenuItem.Header -> VIEW_TYPE_HEADER
            is MenuItem.MealItem -> VIEW_TYPE_MEAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_menu_header, parent, false)
                HeaderViewHolder(view)
            }

            VIEW_TYPE_MEAL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_menu_item, parent, false)
                MealViewHolder(view, clickListener)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is MenuItem.Header -> (holder as HeaderViewHolder).bind(item)
            is MenuItem.MealItem -> {
                (holder as MealViewHolder).bind(item.meal)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

    private fun onMealClickListener(meal: Meal) {
        if (clickDebounce()) {
            clickListener.onMealClick(meal)
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerTextView: TextView = itemView.findViewById(R.id.tv_category_title)

        fun bind(header: MenuItem.Header) {
            headerTextView.text = header.categoryName
        }
    }


    class MealViewHolder(
        private val parentView: View,
        private val clickListener: MealClickListener
    ) :
        RecyclerView.ViewHolder(parentView) {
        private val binding = ListMenuItemBinding.bind(parentView)

        fun bind(meal: Meal) = with(binding) {
            val cornerRadius =
                this@MealViewHolder.parentView.resources.getDimensionPixelSize(R.dimen.image_corner_radius_2)
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

            btAddToCartPrice.text = meal.price.toString() + " ₽"

            Glide.with(parentView)
                .load(meal.imageUrl)
                .centerCrop()
                .transform(RoundedCorners(cornerRadius))
                .placeholder(R.drawable.ic_cover_placeholder)
                .into(ivMealPicture)
            tvMealIngredients.requestLayout()
            ivAddToFavorite.setImageDrawable(getFavoriteDrawable(meal.isFavorite))


            itemView.setOnClickListener { clickListener.onMealClick(meal) }
            ivAddToFavorite.setOnClickListener { clickListener.onFavoriteToggleClick(meal) }
            btAddToCartPrice.setOnClickListener {
                clickListener.onAddToCartClick(meal)
                extraCartButtonsManager(VisibilityStatus.VISIBLE)
            }
            btCartMinus.setOnClickListener { clickListener.minusToCartClick(meal) }
            btCartPlus.setOnClickListener { clickListener.plusToCartClick(meal) }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun getFavoriteDrawable(inFavorite: Boolean): Drawable? {
            return itemView.context.getDrawable(
                if (inFavorite) R.drawable.ic_favorite_active else R.drawable.ic_favorite_inactive
            )
        }

        private fun extraCartButtonsManager(status: VisibilityStatus) {
            when (status) {
                VisibilityStatus.VISIBLE -> binding.apply {
                    btAddToCartPrice.visibility = View.GONE
                    btCartMinus.visibility = View.VISIBLE
                    btCartPlus.visibility = View.VISIBLE
                }

                VisibilityStatus.HIDDEN -> binding.apply {
                    btAddToCartPrice.visibility = View.VISIBLE
                    btCartMinus.visibility = View.GONE
                    btCartPlus.visibility = View.GONE
                }
            }


        }
    }

    interface MealClickListener {
        fun onMealClick(meal: Meal)
        fun onFavoriteToggleClick(meal: Meal)
        fun onAddToCartClick(meal: Meal)
        fun plusToCartClick(meal: Meal)
        fun minusToCartClick(meal: Meal)
    }

    private companion object {
        private const val CLICK_DEBOUNCE_DELAY = 30L
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_MEAL = 1

        enum class VisibilityStatus {
            VISIBLE,
            HIDDEN
        }
    }

}
