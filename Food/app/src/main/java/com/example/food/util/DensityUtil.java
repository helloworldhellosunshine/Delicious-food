package com.example.food.util;

import android.content.Context;

/**
 * Created by 王帝 on 2017/10/2.
 */

public class DensityUtil {
    public static int dip2px(Context context,float dpValue){
        final float scale= context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale+0.5f);
    }
    public static int px2dip(Context context,float pxValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale+ 0.5f);
    }
}
