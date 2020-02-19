package jp.co.myself.psystem_android.utils;

import android.content.Context;
import android.util.TypedValue;

public class ViewUtils {

    public static int fromDipToPixel(int dip, Context context) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                context.getResources().getDisplayMetrics());
    }

}
