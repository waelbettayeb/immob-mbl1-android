package com.waelkhelil.immob

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.immob.model.Intervention
import java.util.*


class InterventionAdapter(private val list: List<Intervention>)
    : RecyclerView.Adapter<InterventionAdapter.InterventionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterventionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return InterventionViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: InterventionViewHolder, position: Int) {
        val lIntervention: Intervention = list[position]
        holder.bind(lIntervention)
    }

    override fun getItemCount(): Int = list.size
    class InterventionViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.intervention_list_item, parent, false)){
        private var mIntervention: Intervention? = null
        fun bind(pIntervention: Intervention) {
            mIntervention = pIntervention
            val locale = Locale.getDefault()

            itemView.findViewById<TextView>(R.id.tv_number).text = pIntervention.mNumber.toString()
            itemView.findViewById<TextView>(R.id.tv_type).text = pIntervention.mType.toString()
            itemView.findViewById<TextView>(R.id.tv_plumber).text = pIntervention.mPlumberName
            itemView.findViewById<TextView>(R.id.tv_date).text = pIntervention.mPostingDate.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale) + ", " +
                    pIntervention.mPostingDate.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) + " " +
                    pIntervention.mPostingDate.get(Calendar.DAY_OF_MONTH) + ", "+
                    pIntervention.mPostingDate.get(Calendar.YEAR) + " "+
                    pIntervention.mPostingDate.get(Calendar.HOUR_OF_DAY) + ":"+
                    pIntervention.mPostingDate.get(Calendar.MINUTE).toString().padStart(2, '0')
        }
    }
}