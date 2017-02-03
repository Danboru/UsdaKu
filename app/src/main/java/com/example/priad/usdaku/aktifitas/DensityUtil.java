package com.example.priad.usdaku.aktifitas;

import android.content.Context;

/**
 * Created by priad on 2/3/2017.
 */

public class DensityUtil {

    private DensityUtil(){}

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}