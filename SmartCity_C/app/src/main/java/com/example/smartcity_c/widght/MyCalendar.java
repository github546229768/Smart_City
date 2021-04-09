package com.example.smartcity_c.widght;

import android.content.Context;
import android.graphics.Canvas;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MultiMonthView;

public class MyCalendar extends MultiMonthView {
    public MyCalendar(Context context) {
        super(context);
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelectedPre, boolean isSelectedNext) {
        return false;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y, boolean isSelected) {

    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        canvas.drawText(String.valueOf(calendar.getDay()), x + mItemWidth / 2, y + mItemHeight / 2, mSelectedLunarTextPaint);
        if (isSelected){
            canvas.drawText("已选择", x + mItemWidth / 2, y + mItemHeight / 2, mCurDayTextPaint);
            canvas.drawCircle(x + mItemWidth / 2, y + mItemHeight / 2,50f,mSelectedPaint);
        }
    }
}
