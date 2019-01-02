package com.tangbba.statusbarapp.widgets;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ScaledImageView extends AppCompatImageView {
    public ScaledImageView(Context context) {
        super(context);
    }

    public ScaledImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaledImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            BitmapDrawable drawable = (BitmapDrawable) getDrawable();
            if (drawable == null) {
                setMeasuredDimension(0,0);
            } else {
                int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
                int width = measuredWidth;
                int height = width * drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth();
                setMeasuredDimension(width, height);
            }
        } catch (Exception e) {
            super.onMeasure(0, 0);
        } finally {
            requestLayout();
        }
    }
}
