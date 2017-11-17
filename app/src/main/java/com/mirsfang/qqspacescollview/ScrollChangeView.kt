package com.mirsfang.qqspacescollview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View

import junit.framework.AssertionFailedError

/**
 * 作者： MirsFang on 2017/11/16 17:19
 * 邮箱： mirsfang@163.com
 * 类描述：
 */

class ScrollChangeView : View {

    private var paint: Paint? = null
    private var screenHeight: Int = 0
    private var topCanVas: Canvas? = null
    private var mHeight: Int = 0
    private var mWidth: Int = 0
    private var radius = 0f
    private var rectF: RectF? = null
    private var bottom: Bitmap? = null
    private var topBg: Bitmap? = null
    private var top: Bitmap? = null
    //X 和 Y 的偏移量
    private var offsetX = 100f
    private var offsetY = 100f
    private var images = intArrayOf(R.mipmap.draw1, R.mipmap.draw2)

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }


    private fun init() {
        getWidthAndHeight()
        paint = Paint()
        paint!!.alpha = 0
        paint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        paint!!.style = Paint.Style.FILL
        paint!!.isAntiAlias = true


        top = (resources.getDrawable(images[0]) as BitmapDrawable).bitmap
        bottom = (resources.getDrawable(images[1]) as BitmapDrawable).bitmap

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        topCanVas!!.drawBitmap(top!!, null, rectF!!, null)
        canvas.drawBitmap(bottom!!, null, rectF!!, null)
        canvas.drawBitmap(topBg!!, null, rectF!!, null)
        topCanVas!!.drawCircle(mWidth - offsetX, mHeight - offsetY, radius, paint!!)

    }

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        mHeight = View.MeasureSpec.getSize(heightMeasureSpec)
        rectF = RectF(0f, 0f, mWidth.toFloat(), mHeight.toFloat())
        topBg = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_4444)
        topCanVas = Canvas(topBg!!)
    }


    fun getWidthAndHeight() {
        val resources = this.resources
        val dm = resources.displayMetrics
        screenHeight = dm.heightPixels
    }

    fun setRecyclerView(recycler: RecyclerView) {
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                getLocation()
            }
        })
    }

    private fun getLocation() {
        val location = IntArray(2)
        getLocationOnScreen(location)
        val y = location[1]
        val height = y + height

        //等图全部出来完之后再开始渲染
        if (y > 0 && screenHeight >= height) {
            radius = (screenHeight - height).toFloat()
            topCanVas!!.drawCircle(this.mWidth - offsetX, this.mHeight - offsetY, radius, paint!!)
        }
        postInvalidate()
    }


    /**
     * 设置X轴的偏移量
     * @param offsetX
     */
    fun setOffsetX(offsetX: Float) {
        this.offsetX = offsetX
    }

    /**
     * 设置Y轴的偏移量
     * @param offsetY
     */
    fun setOffsetY(offsetY: Float) {
        this.offsetY = offsetY
    }

    /**
     * 设置图片
     * @param images
     */
    fun setImages(images: IntArray) {
        if (images.size == 0) {
            throw AssertionFailedError("images length is 0")
        }
        this.images = images
    }

    companion object {
        var TAG = ScrollChangeView::class.java.simpleName
    }
}
