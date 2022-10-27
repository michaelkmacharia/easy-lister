package com.example.easyLister

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_easylister.view.*

class EasyListerAdapter(
		private val easylisters: MutableList<EasyLister>
) : RecyclerView.Adapter<EasyListerAdapter.EasyListerViewHolder>() {

		class EasyListerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EasyListerViewHolder {
				return EasyListerViewHolder(
						LayoutInflater.from(parent.context).inflate(
								R.layout.item_easylister,
								parent,
								false
						)
				)
		}

		fun addEasyLister(easylister: EasyLister) {
				easylisters.add(easylister)
				notifyItemInserted(easylisters.size - 1)
		}

		fun deleteDoneEasyListers() {
				easylisters.removeAll { easylister ->
						easylister.isChecked
				}
				notifyDataSetChanged()
		}

		private fun toggleStrikeThrough(tvEasyListerTitle: TextView, isChecked: Boolean) {
				if(isChecked) {
						tvEasyListerTitle.paintFlags = tvEasyListerTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
				} else {
						tvEasyListerTitle.paintFlags = tvEasyListerTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
				}
		}

		override fun onBindViewHolder(holder: EasyListerViewHolder, position: Int) {
				val curEasyLister = easylisters[position]
				holder.itemView.apply {
						tvEasyListerTitle.text = curEasyLister.title
						cbDone.isChecked = curEasyLister.isChecked
						toggleStrikeThrough(tvEasyListerTitle, curEasyLister.isChecked)
						cbDone.setOnCheckedChangeListener { _, isChecked ->
								toggleStrikeThrough(tvEasyListerTitle, isChecked)
								curEasyLister.isChecked = !curEasyLister.isChecked
						}
				}
		}

		override fun getItemCount(): Int {
				return easylisters.size
		}
}
