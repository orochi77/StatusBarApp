package com.tangbba.statusbarapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;

public class DisplayUtils {
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);

        return displaymetrics;
    }

    public static int getPixelFromDP(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getDisplayMetrics(context));
    }
}
