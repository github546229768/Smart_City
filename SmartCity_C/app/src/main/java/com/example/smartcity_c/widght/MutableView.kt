package com.example.smartcity_c.widght

import android.content.Context
import android.graphics.Canvas
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.MultiMonthView

class MutableView(context: Context?) : MultiMonthView(context) {
    override fun onDrawSelected(
        canvas: Canvas?,
        calendar: Calendar?,
        x: Int,
        y: Int,
        hasScheme: Boolean,
        isSelectedPre: Boolean,
        isSelectedNext: Boolean
    ) = false

    override fun onDrawScheme(
        canvas: Canvas?,
        calendar: Calendar?,
        x: Int,
        y: Int,
        isSelected: Boolean
    ) {
    }

    override fun onDrawText(
        canvas: Canvas?,
        calendar: Calendar?,
        x: Int,
        y: Int,
        hasScheme: Boolean,
        isSelected: Boolean
    ) {
        val mx = x + mItemWidth /2
        val my = y + mItemHeight /2
        canvas?.drawText(calendar?.day.toString(), (mx).toFloat(), (my).toFloat(),
                if (calendar?.isCurrentDay == true) mCurDayTextPaint else if (calendar?.isCurrentMonth == true) mCurMonthTextPaint else mOtherMonthTextPaint)
        if (isSelected){
            canvas?.drawText(calendar?.lunar.toString(), mx.toFloat(), mTextBaseLine + y+10 , mSelectedLunarTextPaint);
        }
    }
}