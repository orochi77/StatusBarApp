package com.tangbba.statusbarapp.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.utils.DisplayUtils;

public class IconItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "IconItemDecoration";

    private Context mContext;
    private Drawable mSpaceDividerDrawable;
    private Drawable mLineDividerDrawable;

    private Paint mPaint;

    public IconItemDecoration(Context context) {
        mContext = context;

        mLineDividerDrawable = ContextCompat.getDrawable(mContext, R.drawable.line_divider);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DisplayUtils.getPixelFromDP(mContext, 1));
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
//        super.onDrawOver(canvas, recyclerView, state);
        drawDivider(canvas, recyclerView);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getChildCount() == 0) {
            return;
        }

        final int childCount = recyclerView.getChildCount();
        final int rowCount = childCount / 4 + 1;

        int itemPosition = recyclerView.getChildLayoutPosition(view);
        int offset = DisplayUtils.getPixelFromDP(mContext, 3);
        int offset2 = DisplayUtils.getPixelFromDP(mContext, 8);

        // 컬럼 여백
        int left;
        int right;
        if (itemPosition % 4 == 0) {
            left = DisplayUtils.getPixelFromDP(mContext, 0);
            right = DisplayUtils.getPixelFromDP(mContext, 3);
        } else if (itemPosition % 4 == 3) {
            left = DisplayUtils.getPixelFromDP(mContext, 3);
            right = DisplayUtils.getPixelFromDP(mContext, 0);
        } else {
            left = DisplayUtils.getPixelFromDP(mContext, 3);
            right = DisplayUtils.getPixelFromDP(mContext, 3);
        }

        // 로우 여백
        int top;
        int bottom;
        if ((int) itemPosition / 4 == 0) {
            top = DisplayUtils.getPixelFromDP(mContext, 3);
            bottom = DisplayUtils.getPixelFromDP(mContext, 3);
        } else if (itemPosition / 4 == rowCount) {
            top = DisplayUtils.getPixelFromDP(mContext, 3);
            bottom = DisplayUtils.getPixelFromDP(mContext, 3);
        } else if (itemPosition / 4 == 1) {
            top = DisplayUtils.getPixelFromDP(mContext, 3);
            bottom = DisplayUtils.getPixelFromDP(mContext, 8);
        } else if (itemPosition / 4 == 2) {
            top = DisplayUtils.getPixelFromDP(mContext, 8);
            bottom = DisplayUtils.getPixelFromDP(mContext, 3);
        } else {
            top = DisplayUtils.getPixelFromDP(mContext, 3);
            bottom = DisplayUtils.getPixelFromDP(mContext, 3);
        }

//        outRect.left = offset;
//        outRect.right = offset;
//        outRect.top = top;
//        outRect.bottom = bottom;

        outRect.left = 0;
        outRect.right = 0;
        outRect.top = 0;
        outRect.bottom = 0;
    }

    private void drawDivider(Canvas canvas, RecyclerView recyclerView) {
        if (recyclerView.getChildCount() == 0) {
            return;
        }

        int offset = DisplayUtils.getPixelFromDP(mContext, 8);
        final int childCount = recyclerView.getChildCount();
        final int rowCount = childCount / 4 + 1;

        for (int i = 0; i < childCount; i++) {
            View child = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if (i / 4 == 1) {
                int left = recyclerView.getPaddingLeft() + DisplayUtils.getPixelFromDP(mContext, 6);
                int right = recyclerView.getWidth() - DisplayUtils.getPixelFromDP(mContext, 6);;
                int top = child.getBottom() + offset;
                int bottom = child.getTop() + 1;
//                mLineDividerDrawable.setBounds(left, top, right, bottom);
//                canvas.drawLine(left, top, right, top, mPaint);
//                canvas.drawRect(left, top, right, bottom, mPaint);
//                mLineDividerDrawable.draw(canvas);
            }
        }
    }
}
