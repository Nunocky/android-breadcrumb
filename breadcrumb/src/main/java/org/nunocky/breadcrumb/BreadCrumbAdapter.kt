package org.nunocky.breadcrumb

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.nunocky.breadcrumb.BreadCrumbView.BreadCrumbItem
import us.smailbarkouch.android_breadcrumb.R

private val diffCallback = object : DiffUtil.ItemCallback<BreadCrumbItem>() {
    override fun areItemsTheSame(oldItem: BreadCrumbItem, newItem: BreadCrumbItem) =
        oldItem.title == newItem.title // check uniqueness

    override fun areContentsTheSame(oldItem: BreadCrumbItem, newItem: BreadCrumbItem) =
        oldItem.title == newItem.title // check contents
}

internal class BreadCrumbAdapter(var breadCrumbItemClickListener: BreadCrumbItemClickListener) :
    ListAdapter<BreadCrumbItem, BreadCrumbAdapter.ViewHolder>(diffCallback) {

    private var mBreadCrumbItemItemsData: MutableList<BreadCrumbItem> = mutableListOf()
    private var arrowDrawable: Int = R.drawable.ic_baseline_keyboard_arrow_right_24
    private var textColor: Int = Color.WHITE
    private var textSize: Int = 14
    private var clickable: Boolean = true
    private var currentPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.bread_crumb_item, parent, false)
        )
    }

    override fun getItemCount(): Int = mBreadCrumbItemItemsData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mBreadCrumbItemItemsData[position], position)
    }

    fun getBreadCrumbItem(position: Int) = mBreadCrumbItemItemsData[position]

    fun getBreadCrumbItemsSize(): Int = mBreadCrumbItemItemsData.size

    fun removeLastBreadCrumbItem() {
        mBreadCrumbItemItemsData.removeLast()
        notifyDataSetChanged()
    }

    fun removeAllBreadCrumbItems() {
        mBreadCrumbItemItemsData.removeAll { true }
        notifyDataSetChanged()
    }

    fun addBreadCrumbItem(item: BreadCrumbItem) {
        mBreadCrumbItemItemsData.add(item)
        notifyDataSetChanged()
    }

    fun setBreadCrumbItems(items: MutableList<BreadCrumbItem>) {
        mBreadCrumbItemItemsData = items
        notifyDataSetChanged()
    }

    fun setArrowDrawable(arrowDrawable: Int) {
        this.arrowDrawable = arrowDrawable
        notifyDataSetChanged()
    }

    fun setTextColor(textColor: Int) {
        this.textColor = textColor
        notifyDataSetChanged()
    }

    fun setTextSize(textSize: Int) {
        this.textSize = textSize
        notifyDataSetChanged()
    }

    fun setClickable(clickable: Boolean) {
        this.clickable = clickable
        notifyDataSetChanged()
    }

    fun setPosition(position: Int) {
        this.currentPosition = position
        notifyDataSetChanged()
    }

    inner class ViewHolder(breadCrumbItem: View) : RecyclerView.ViewHolder(breadCrumbItem) {
        var breadCrumbTitle: TextView = itemView.findViewById(R.id.bread_crumb_title)
        var breadCrumbSeparator: ImageView = itemView.findViewById(R.id.bread_crumb_separator)

        init {
            breadCrumbTitle.setOnClickListener { view ->
                breadCrumbItemClickListener.onItemClick(view, adapterPosition)
            }

            breadCrumbSeparator.setImageResource(arrowDrawable)
            breadCrumbTitle.setTextColor(textColor)
            breadCrumbSeparator.setColorFilter(textColor)
            breadCrumbTitle.textSize = textSize.toFloat()
        }

        fun bind(item: BreadCrumbItem, position: Int) {
            if (position == 0) {
                breadCrumbSeparator.visibility = View.GONE
            } else {
                breadCrumbSeparator.visibility = View.VISIBLE
            }

            breadCrumbTitle.text = item.title

            if (position == currentPosition) {
                breadCrumbTitle.typeface = Typeface.DEFAULT_BOLD
            } else {
                breadCrumbTitle.typeface = Typeface.DEFAULT
            }

            breadCrumbTitle.isClickable = clickable
        }
    }
}

interface BreadCrumbItemClickListener {
    fun onItemClick(breadCrumbItem: View, position: Int)
}
