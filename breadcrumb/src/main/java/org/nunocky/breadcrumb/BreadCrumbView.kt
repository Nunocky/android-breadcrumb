package org.nunocky.breadcrumb

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import us.smailbarkouch.android_breadcrumb.R


// TODO
//  - focused_text_color対応
//  -

class BreadCrumbView : FrameLayout {
    data class BreadCrumbItem(
        val title: String
    )

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var breadCrumbAdapter: BreadCrumbAdapter

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        (layoutParams as LayoutParams?)?.let {
            it.gravity = Gravity.CENTER
        }

        createAndAddRecyclerView(context)

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.BreadCrumbView, defStyleAttr, 0)

            val arrowDrawable =
                typedArray.getResourceId(R.styleable.BreadCrumbView_arrow_drawable, -1)
            val textColor = typedArray.getColor(R.styleable.BreadCrumbView_text_color, -1)
            val textSize = typedArray.getColor(R.styleable.BreadCrumbView_text_size, -1)
            val clickable = typedArray.getBoolean(R.styleable.BreadCrumbView_clickable, true)
            val entriesId = typedArray.getResourceId(R.styleable.BreadCrumbView_entries, -1)
            val focusedTextColor =
                typedArray.getColor(R.styleable.BreadCrumbView_focused_text_color, -1)

            typedArray.recycle()

            breadCrumbAdapter.setClickable(clickable)

            if (arrowDrawable != -1) {
                breadCrumbAdapter.setArrowDrawable(arrowDrawable)
            }
            if (textColor != -1) {
                breadCrumbAdapter.setTextColor(textColor)
            }
            if (textSize != -1) {
                breadCrumbAdapter.setTextSize(textSize)
            }
            if (focusedTextColor != -1) {
                breadCrumbAdapter.setFocuedTextColor(focusedTextColor)
            }

            if (entriesId != -1) {
                val entries = resources.getTextArray(entriesId)
                entries.forEach {
                    addBreadCrumbItem(BreadCrumbItem(it.toString()))
                }
            }
        }
    }

    private fun createAndAddRecyclerView(context: Context) {
        recyclerView = RecyclerView(context)
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        breadCrumbAdapter = BreadCrumbAdapter(object : BreadCrumbItemClickListener {
            override fun onItemClick(breadCrumbItem: View, position: Int) {}
        })

        recyclerView.adapter = breadCrumbAdapter

        addView(recyclerView, layoutParams)
    }

    fun addBreadCrumbItem(item: BreadCrumbItem) {
        breadCrumbAdapter.addBreadCrumbItem(item)
        recyclerView.smoothScrollToPosition(breadCrumbAdapter.getBreadCrumbItemsSize() - 1)
    }

    fun setItemClickListener(listener: BreadCrumbItemClickListener) {
        breadCrumbAdapter.breadCrumbItemClickListener = listener
    }

    fun setArrowDrawable(arrowDrawable: Int) = breadCrumbAdapter.setArrowDrawable(arrowDrawable)
    fun setBreadCrumbItems(items: MutableList<BreadCrumbItem>) {
        breadCrumbAdapter.setBreadCrumbItems(items)
        recyclerView.smoothScrollToPosition(breadCrumbAdapter.getBreadCrumbItemsSize() - 1)
    }

    fun setTextColor(textColor: Int) = breadCrumbAdapter.setTextColor(textColor)
    fun setTextSize(textSize: Int) = breadCrumbAdapter.setTextSize(textSize)
    fun getBreadCrumbItem(position: Int) = breadCrumbAdapter.getBreadCrumbItem(position)
    fun removeAllBreadCrumbItems() = breadCrumbAdapter.removeAllBreadCrumbItems()
    fun removeLastBreadCrumbItem() = breadCrumbAdapter.removeLastBreadCrumbItem()
    override fun setClickable(clickable: Boolean) {
        super.setClickable(clickable)
        breadCrumbAdapter.setClickable(clickable)
    }

    fun setPosition(position: Int) = breadCrumbAdapter.setPosition(position)
}
