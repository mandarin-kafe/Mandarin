package com.mandarinkafe.mandarin.meal_details.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.ListAdditionalsItemBinding
import com.mandarinkafe.mandarin.menu.domain.models.Item

class MealAdditionalsAdapter(
    private val additionals: ArrayList<Item>,
    private val clickListener: AddsClickListener
) :
    RecyclerView.Adapter<MealAdditionalsAdapter.AdditionalsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_additionals_item, parent, false)
        return AdditionalsViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: AdditionalsViewHolder, position: Int) {
        holder.bind(additionals[position])
    }


    override fun getItemCount(): Int = additionals.size


    class AdditionalsViewHolder(itemView: View, private val clickListener: AddsClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ListAdditionalsItemBinding.bind(itemView)
        private var numberInCart =
            0 // TODO временная переменная! брать цифру из логики корзины, а то холдер одну и ту же переменную привязывает к разным товарам


        fun bind(item: Item) {
            setOnClickListeners(item)
            binding.apply {
                tvMealTitle.text =
                    itemView.context.getString(
                        R.string.meal_title_with_weight_template,
                        item.name,
                        item.weight
                    )

                tvMealPrice.text =
                    itemView.context.getString(R.string.meal_price_template, item.price)
            }
        }

        private fun setOnClickListeners(item: Item) = with(binding) {

            btCartMinus.setOnClickListener {
                clickListener.minusToCartClick(item)

                tvNumberInCart.text =
                    itemView.context.getString(
                        R.string.meal_in_cart_count_template,
                        --numberInCart
                    )
                tvTotalPriceInCart.text =
                    itemView.context.getString(
                        R.string.meal_price_template,
                        item.price * numberInCart
                    )

                if (numberInCart <= 0) {
                    extraCartButtonsManager(inCart = false)
                }
            }
            btCartPlus.setOnClickListener {
                clickListener.plusToCartClick(item)
                extraCartButtonsManager(inCart = true)
                tvNumberInCart.text =
                    itemView.context.getString(
                        R.string.meal_in_cart_count_template,
                        ++numberInCart
                    )

                tvTotalPriceInCart.text =
                    itemView.context.getString(
                        R.string.meal_price_template,
                        item.price * numberInCart
                    )
            }
        }

        private fun extraCartButtonsManager(inCart: Boolean) {
            when (inCart) {
                true -> binding.apply {
                    tvMealPrice.visibility = View.GONE
                    groupExtraCartButtons.visibility= View.VISIBLE

                }

                false -> binding.apply {
                    tvMealPrice.visibility = View.VISIBLE
                    groupExtraCartButtons.visibility= View.GONE
                }
            }
        }
    }


    interface AddsClickListener {
        fun plusToCartClick(additional: Item)
        fun minusToCartClick(additional: Item)

    }
}
