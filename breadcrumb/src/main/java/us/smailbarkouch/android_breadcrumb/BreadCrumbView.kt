package us.smailbarkouch.android_breadcrumb

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class BreadCrumb(
    val title: String
)

class BreadCrumbView : FrameLayout {

    private lateinit var recyclerView: RecyclerView
    private lateinit var breadCrumbAdapter: BreadCrumbAdapter

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        createAndAddRecyclerView(context)

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.BreadCrumbView, defStyleAttr, 0)

            val clickable = typedArray.getBoolean(R.styleable.BreadCrumbView_clickable, true)
            val arrowDrawable =
                typedArray.getResourceId(R.styleable.BreadCrumbView_arrow_drawable, -1)
            val textColor = typedArray.getColor(R.styleable.BreadCrumbView_text_color, -1)
            val textSize = typedArray.getColor(R.styleable.BreadCrumbView_text_size, -1)
            val backGround =
                typedArray.getResourceId(R.styleable.BreadCrumbView_background_drawable, -1)
            typedArray.recycle()

//            breadCrumbAdapter.setClickable(clickable)

            if (arrowDrawable != -1) {
                breadCrumbAdapter.setArrowDrawable(arrowDrawable)
            }
            if (textColor != -1) {
                breadCrumbAdapter.setTextColor(textColor)
            }
            if (textSize != -1) {
                breadCrumbAdapter.setTextSize(textSize)
            }
            if (backGround != -1) {
                setBackgroundResource(backGround)
            }
        }

        this.setPadding(40, 0, 40, 0)
    }

    private fun createAndAddRecyclerView(context: Context) {
        recyclerView = RecyclerView(context)
        val recyclerViewParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        breadCrumbAdapter = BreadCrumbAdapter(object : BreadCrumbItemClickListener {
            override fun onItemClick(breadCrumbItem: View, position: Int) {}
        })

        recyclerView.adapter = breadCrumbAdapter

        addView(recyclerView, recyclerViewParams)
    }

    fun addBreadCrumbItem(item: BreadCrumb) {
        breadCrumbAdapter.addBreadCrumbItem(item)
        recyclerView.smoothScrollToPosition(breadCrumbAdapter.getBreadCrumbItemsSize() - 1)
    }

    fun setListener(listener: BreadCrumbItemClickListener) {
        breadCrumbAdapter.breadCrumbItemClickListener = listener
    }

    fun setArrowDrawable(arrowDrawable: Int) = breadCrumbAdapter.setArrowDrawable(arrowDrawable)
    fun setBreadCrumbItems(items: MutableList<BreadCrumb>) {
        breadCrumbAdapter.setBreadCrumbItems(items)
        recyclerView.smoothScrollToPosition(breadCrumbAdapter.getBreadCrumbItemsSize() - 1)
    }

    fun setTextColor(textColor: Int) = breadCrumbAdapter.setTextColor(textColor)
    fun setTextSize(textSize: Int) = breadCrumbAdapter.setTextSize(textSize)
    fun getBreadCrumbItem(position: Int) = breadCrumbAdapter.getBreadCrumbItem(position)
    fun removeAllBreadCrumbItems() = breadCrumbAdapter.removeAllBreadCrumbItems()
    fun removeLastBreadCrumbItem() = breadCrumbAdapter.removeLastBreadCrumbItem()

//    fun setClickable(clickable: Boolean) {
//        // TODO recyclerViewの子すべてに clickableを反映する
//        recyclerView.children.forEach { v ->
//            v.isEnabled = clickable
//        }
//    }

    fun setPosition(position: Int) {
        TODO("IMPLEMENT THIS")
    }
}
