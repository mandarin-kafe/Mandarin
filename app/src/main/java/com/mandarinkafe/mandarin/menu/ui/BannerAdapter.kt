package com.mandarinkafe.mandarin.menu.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandarinkafe.mandarin.R
import com.mandarinkafe.mandarin.menu.domain.models.Banner

class BannerAdapter(
    private val banners: List<Banner>
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = banners[position]
        val imageView: ImageView = holder.itemView.findViewById(R.id.iv_banner)

        Glide.with(holder.itemView)
            .load(banner.imageUrl)
            .fitCenter()
                    .placeholder(R.drawable.ic_cover_placeholder)
            .into(imageView)


        holder.itemView.setOnClickListener {
//           TODO banner.categoryOnClick
        }
    }

    override fun getItemCount(): Int {
        return banners.size
    }
}
