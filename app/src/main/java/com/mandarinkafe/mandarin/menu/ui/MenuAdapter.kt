package com.mandarinkafe.mandarin.menu.ui

import android.annotation.SuppressLint
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
import com.mandarinkafe.mandarin.menu.domain.models.Item
import com.mandarinkafe.mandarin.menu.domain.models.MenuItem


class MenuAdapter(
    private val clickListener: MealClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var isClickAllowed = true
    private val handler = Handler(Looper.getMainLooper())
    private var items: List<MenuItem> = listOf()

    fun setMenuList(menuData: List<MenuItem>) {
        items = menuData
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MenuItem.MealItem -> VIEW_TYPE_MEAL
            is MenuItem.SubCategory -> VIEW_TYPE_SUB_HEADER
            is MenuItem.Category -> VIEW_TYPE_PARENT_HEADER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MEAL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_menu_item, parent, false)
                MealViewHolder(view, clickListener)
            }

            VIEW_TYPE_SUB_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_menu_header, parent, false)
                HeaderViewHolder(view)
            }

            VIEW_TYPE_PARENT_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_menu_parent_header, parent, false)
                HeaderViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is MenuItem.MealItem -> {
                (holder as MealViewHolder).bind(item.meal)
            }

            is MenuItem.SubCategory -> (holder as HeaderViewHolder).bind(item)
            is MenuItem.Category -> (holder as HeaderViewHolder).bind(item)


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


    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerTextView: TextView = itemView.findViewById(R.id.tv_category_title)
        fun bind(category: MenuItem.Category) {
            headerTextView.text = category.categoryName
        }
    }

    class MealViewHolder(
        private val parentView: View,
        private val clickListener: MealClickListener
    ) :
        RecyclerView.ViewHolder(parentView) {
        private val binding = ListMenuItemBinding.bind(parentView)
        private var numberInCart = 0
        // TODO временная переменная! брать цифру из логики корзины, а то холдер одну и ту же переменную привязывает к разным товарам

        private val cornerRadius =
            this@MealViewHolder.parentView.resources.getDimensionPixelSize(R.dimen.image_corner_radius_2)

        fun bind(item: Item) {
            setMealData(item)
            setOnClickListeners(item)
            if (item.categoryId != "pizza") {
                binding.ivEditMeal.isVisible = false
            } else {
                binding.ivEditMeal.isVisible = true
            }
        }

        private fun setMealData(item: Item) = with(binding) {
            tvMealTitle.text = item.name

            if (item.description.isNullOrEmpty()) {
                tvMealIngredients.isVisible = false
            } else {
                tvMealIngredients.isVisible = true
                tvMealIngredients.text = item.description
            }
            if (item.weight == null || item.weight == 0) {
                tvMealWeight.isVisible = false
            } else {
                tvMealWeight.isVisible = true
                tvMealWeight.text =
                    parentView.context.getString(R.string.meal_weight_template, item.weight)
            }
            btAddToCartPrice.text =
                parentView.context.getString(R.string.meal_price_template, item.price)

            Glide.with(parentView)
                .load(item.imageUrl)
                .centerCrop()
                .transform(RoundedCorners(cornerRadius))
                .placeholder(R.drawable.logo_orange)
                .into(ivMealPicture)
            tvMealIngredients.requestLayout()

            ivAddToFavorite.setImageResource(getFavoriteDrawable(item.isFavorite))
        }

        private fun setOnClickListeners(item: Item) = with(binding) {
            parentView.setOnClickListener { clickListener.onMealClick(item) }
            ivAddToFavorite.setOnClickListener {
                clickListener.onFavoriteToggleClick(item, getBindingAdapterPosition())
                toggleFavorite(item)
            }
            ivEditMeal.setOnClickListener { clickListener.onEditClick(item) }
            btAddToCartPrice.setOnClickListener {
                clickListener.onAddToCartClick(item)
                onPlusClick(item)
                extraCartButtonsManager(inCart = true)
            }

            btCartMinus.setOnClickListener {
                clickListener.minusToCartClick(item)
                onMinusClick(item)
            }
            btCartPlus.setOnClickListener {
                clickListener.plusToCartClick(item)
                onPlusClick(item)
            }
        }

        private fun onPlusClick(item: Item) = with(binding) {
            tvNumberInCart.text =
                parentView.context.getString(
                    R.string.meal_in_cart_count_template,
                    ++numberInCart
                )

            tvTotalPriceInCart.text =
                parentView.context.getString(
                    R.string.meal_price_template,
                    item.price * numberInCart
                )
        }

        private fun onMinusClick(item: Item) = with(binding) {

            tvNumberInCart.text =
                parentView.context.getString(
                    R.string.meal_in_cart_count_template,
                    --numberInCart
                )
            tvTotalPriceInCart.text =
                parentView.context.getString(
                    R.string.meal_price_template,
                    item.price * numberInCart
                )
            if (numberInCart == 0) {
                extraCartButtonsManager(inCart = false)
            }


        }


        private fun getFavoriteDrawable(inFavorite: Boolean): Int {
            val drawableId =
                if (inFavorite) R.drawable.ic_favorite_active else R.drawable.ic_favorite_inactive
            return drawableId
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun toggleFavorite(item: Item) {
            val newDrawableId = getFavoriteDrawable(!item.isFavorite)

            binding.ivAddToFavorite.apply {
                animate()
                    .alpha(0f) // Прозрачность 0
                    .setDuration(150)
                    .withEndAction { // Меняем изображение, когда оно исчезнет
                        setImageResource(newDrawableId)

                        animate()
                            .alpha(1f) // Прозрачность 1
                            .setDuration(150)
                            .start()
                    }
                    .start()
            }
            item.isFavorite = !item.isFavorite
        }

        private fun extraCartButtonsManager(inCart: Boolean) {
            when (inCart) {
                true -> binding.apply {
                    btAddToCartPrice.visibility = View.GONE
                    groupExtraCartButtons.visibility = View.VISIBLE
                }

                false -> binding.apply {
                    btAddToCartPrice.visibility = View.VISIBLE
                    groupExtraCartButtons.visibility = View.GONE
                }
            }
        }
    }

    interface MealClickListener {
        fun onMealClick(item: Item)
        fun onFavoriteToggleClick(item: Item, position: Int)
        fun onAddToCartClick(item: Item)
        fun onEditClick(item: Item)
        fun plusToCartClick(item: Item)
        fun minusToCartClick(item: Item)

    }

    private companion object {
        private const val CLICK_DEBOUNCE_DELAY = 30L
        const val VIEW_TYPE_PARENT_HEADER = 0
        const val VIEW_TYPE_MEAL = 1
        const val VIEW_TYPE_SUB_HEADER = 2

    }
}
