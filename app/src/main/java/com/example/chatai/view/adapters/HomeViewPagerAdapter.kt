package com.example.chatai.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.util.getColumnIndex
import androidx.viewpager.widget.PagerAdapter
import com.example.chatai.R
import com.example.chatai.utils.Extensions.debug
import java.util.Objects


class HomeViewPagerAdapter(var context: Context
) : PagerAdapter() {

    private var images: Array<Int> = arrayOf(
        R.drawable.home_bot,
        R.drawable.robotvactor,
        R.drawable.home)

    private var mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val index = position % images.size
        val itemView: View = mLayoutInflater.inflate(R.layout.home_image_item, container, false)
        val imageView = itemView.findViewById<View>(R.id.imageViewMain) as ImageView
        imageView.setImageResource(images[index])
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}