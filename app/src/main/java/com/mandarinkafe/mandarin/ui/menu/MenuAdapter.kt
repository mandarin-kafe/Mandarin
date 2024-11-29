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
                // TODO Установку OnClickListener можно вынести из Холдера сюда, тогда можно будет использовать clickDebounce,
                // ну и не придётся передавать в Холдер обьект clickListener.

            }
        }
    }

    private fun onMealClickListener(meal: Meal) {
        if (clickDebounce()) {
            clickListener.onMealClick(meal)
        }
    }

    private fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

    override fun getItemCount(): Int {
        return items.size
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
        private var numberInCart = 0 // TODO временная переменная! брать цифру из логики корзины
        // Потом брать эту цифру из логики корзины, а то Холдер одну и ту же переменную привязывает к разным товарам

        private val cornerRadius =
            this@MealViewHolder.parentView.resources.getDimensionPixelSize(R.dimen.image_corner_radius_2)

        fun bind(meal: Meal) {
            setMealData(meal)
            setOnClickListeners(meal)
            if (meal.category != "pizza") {binding.ivEditMeal.isVisible = false} else {binding.ivEditMeal.isVisible = true}
        }

        private fun setMealData(meal: Meal) = with(binding) {
            tvMealTitle.text = meal.name
            if (meal.description.isNullOrEmpty()) {
                tvMealIngredients.isVisible = false
            } else {
                tvMealIngredients.isVisible = true
                tvMealIngredients.text = meal.description
            }
            if (meal.weight == null) {
                tvMealWeight.isVisible = false
            } else {
                tvMealWeight.isVisible = true
                tvMealWeight.text = "${meal.weight} г"
            }
            btAddToCartPrice.text = "${meal.price} ₽"

            Glide.with(parentView)
                .load(meal.imageUrl)
                .centerCrop()
                .transform(RoundedCorners(cornerRadius))
                .placeholder(R.drawable.ic_cover_placeholder)
                .into(ivMealPicture)
            tvMealIngredients.requestLayout()
            ivAddToFavorite.setImageDrawable(getFavoriteDrawable(meal.isFavorite))
        }

        private fun setOnClickListeners(meal: Meal) = with(binding) {
            parentView.setOnClickListener { clickListener.onMealClick(meal)}
            ivAddToFavorite .setOnClickListener {
                clickListener.onFavoriteToggleClick(
                    meal
                )
            }
            ivEditMeal.setOnClickListener { clickListener.onEditClick(meal) }
            btAddToCartPrice.setOnClickListener {
                clickListener.onAddToCartClick(meal)
                tvNumberInCart.text = (++numberInCart).toString() + " шт"
                tvTotalPriceInCart.text = (meal.price * numberInCart).toString() + " ₽"
                extraCartButtonsManager(inCart = true)
            }

            btCartMinus.setOnClickListener {
                clickListener.minusToCartClick(meal)
                tvNumberInCart.text = (--numberInCart).toString() + " шт"
                tvTotalPriceInCart.text = (meal.price * numberInCart).toString() + " ₽"
                if (numberInCart == 0) {
                    extraCartButtonsManager(inCart = false)
                }
            }
            btCartPlus.setOnClickListener {
                clickListener.plusToCartClick(meal)
                tvNumberInCart.text = (++numberInCart).toString() + " шт"
                tvTotalPriceInCart.text = (meal.price * numberInCart).toString() + " ₽"
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun getFavoriteDrawable(inFavorite: Boolean): Drawable? {
            return itemView.context.getDrawable(
                if (inFavorite) R.drawable.ic_favorite_active else R.drawable.ic_favorite_inactive
            )
        }

        private fun extraCartButtonsManager(inCart: Boolean) {
            when (inCart) {
                true -> binding.apply {
                    btAddToCartPrice.visibility = View.GONE
                    btCartMinus.visibility = View.VISIBLE
                    btCartPlus.visibility = View.VISIBLE
                    tvNumberInCart.visibility = View.VISIBLE
                    tvTotalPriceInCart.visibility = View.VISIBLE
                }

                false -> binding.apply {
                    btAddToCartPrice.visibility = View.VISIBLE
                    btCartMinus.visibility = View.GONE
                    btCartPlus.visibility = View.GONE
                    tvNumberInCart.visibility = View.GONE
                    tvTotalPriceInCart.visibility = View.GONE
                }
            }
        }
    }

    interface MealClickListener {
        fun onMealClick(meal: Meal)
        fun onFavoriteToggleClick(meal: Meal)
        fun onAddToCartClick(meal: Meal)
        fun onEditClick(meal: Meal)
        fun plusToCartClick(meal: Meal)
        fun minusToCartClick(meal: Meal)

    }

    private companion object {
        private const val CLICK_DEBOUNCE_DELAY = 30L
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_MEAL = 1

    }
}
