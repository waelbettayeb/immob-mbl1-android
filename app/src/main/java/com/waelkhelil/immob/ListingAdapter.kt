package com.waelkhelil.immob

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.immob.model.Listing
import java.util.*
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity




class ListingAdapter(private val list: List<Listing>, val context:Context)
    : RecyclerView.Adapter<ListingAdapter.ListingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListingViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        val lListing: Listing = list[position]
        holder.bind(lListing, context)
    }

    override fun getItemCount(): Int = list.size
    class ListingViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.lisiting_list_item, parent, false)){
        private var mListing: Listing? = null


        init {
        }

        fun bind(pListing: Listing, context:Context) {
            mListing = pListing
            val locale = Locale.getDefault()

            itemView.findViewById<TextView>(R.id.tv_title).text = pListing.mTitle
            itemView.findViewById<TextView>(R.id.tv_type).text = pListing.mType.toString()
            itemView.findViewById<TextView>(R.id.tv_price).text = pListing.mPrice
            itemView.findViewById<TextView>(R.id.tv_date).text = pListing.mPostingDate.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale) + ", " +
                    pListing.mPostingDate.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) + " " +
                    pListing.mPostingDate.get(Calendar.DAY_OF_MONTH) + ", "+
                    pListing.mPostingDate.get(Calendar.YEAR) + " "+
                    pListing.mPostingDate.get(Calendar.HOUR_OF_DAY) + ":"+
                    pListing.mPostingDate.get(Calendar.MINUTE).toString().padStart(2, '0')
            itemView.findViewById<Button>(R.id.button_phone_number).text = pListing.mPhoneNumber

            itemView.findViewById<Button>(R.id.button_phone_number).setOnClickListener {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:" + pListing.mPhoneNumber)//change the number
                startActivity(context, callIntent,null)
            }
            itemView.findViewById<Button>(R.id.button_place).setOnClickListener {
                val gmmIntentUri = Uri.parse("geo:${pListing.location.latitude},${pListing.location.longitude}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                if (mapIntent.resolveActivity(context.packageManager) != null) {
                    startActivity(context, mapIntent, null)
                }
            }
            val recyclerView = itemView.findViewById(R.id.recyclerView) as RecyclerView
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = ImageAdapter(pListing.mBitmaps)
        }
    }
}