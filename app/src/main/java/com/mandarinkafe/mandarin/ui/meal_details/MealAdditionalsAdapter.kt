package com.mandarinkafe.mandarin.ui.meal_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.databinding.ListAdditionalsItemBinding
import com.mandarinkafe.mandarin.domain.models.Meal

class MealAdditionalsAdapter(
    private val additionals: ArrayList<Meal>,
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


        fun bind(item: Meal) {
            setOnClickListeners(item)
            binding.apply {
                tvMealTitle.text = item.name + ", " + item.weight.toString() + " г"
                tvMealPrice.text = item.price.toString() + " ₽"


            }
        }

        private fun setOnClickListeners(item: Meal) = with(binding) {

            btCartMinus.setOnClickListener {
                clickListener.minusToCartClick(item)
                tvNumberInCart.text = (--numberInCart).toString() + " шт"
                tvTotalPriceInCart.text = (item.price * numberInCart).toString() + " ₽"
                if (numberInCart == 0) {
                    extraCartButtonsManager(inCart = false)
                }
            }
            btCartPlus.setOnClickListener {
                clickListener.plusToCartClick(item)
                extraCartButtonsManager(inCart = true)
                tvNumberInCart.text = (++numberInCart).toString() + " шт"
                tvTotalPriceInCart.text = (item.price * numberInCart).toString() + " ₽"
            }
        }

        private fun extraCartButtonsManager(inCart: Boolean) {
            when (inCart) {
                true -> binding.apply {
                    btCartMinus.visibility = View.VISIBLE
backgroundForCartInfo.visibility = View.VISIBLE
                    tvNumberInCart.visibility = View.VISIBLE
                    tvTotalPriceInCart.visibility = View.VISIBLE
                    tvMealPrice.visibility = View.GONE
                }

                false -> binding.apply {
                    btCartMinus.visibility = View.GONE
                    backgroundForCartInfo.visibility = View.GONE
                    tvNumberInCart.visibility = View.GONE
                    tvTotalPriceInCart.visibility = View.GONE
                    tvMealPrice.visibility = View.VISIBLE
                }
            }
        }
    }


    interface AddsClickListener {
        fun plusToCartClick(additional: Meal)
        fun minusToCartClick(additional: Meal)

    }
}
