package com.example.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatTextView
import com.example.app.R
import com.example.base.utils.Utils
import kotlin.random.Random

class CodeView (context: Context,attrs: AttributeSet?): AppCompatTextView(context,attrs) {

    /**
     * 作用域函数 applu 没有返回值 经常用于初始化
     */
    private val paint = Paint().apply {
        isAntiAlias=true
        style= Paint.Style.STROKE
        setColor(getContext().getColor(com.google.android.material.R.color.design_default_color_background))
        strokeWidth = Utils.dp2px(6f)
    }
    private val codeList = arrayOf(
        "kotlin",
        "android",
        "java",
        "http",
        "https",
        "okhttp",
        "retrofit",
        "tcp/ip"
    )

     constructor(context : Context) : this(context,null)

     init{
         setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
         gravity=Gravity.CENTER
         setBackgroundColor(getContext().getColor(com.google.android.material.R.color.design_dark_default_color_primary_dark))
         setTextColor(Color.WHITE)
         updateCode();
     }

       fun updateCode(){
        val random = Random.nextInt(codeList.size)
        val code = codeList.get(random)
        text = code
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(0f,height.toFloat(),width.toFloat(),0f,paint)
        super.onDraw(canvas)
    }

}