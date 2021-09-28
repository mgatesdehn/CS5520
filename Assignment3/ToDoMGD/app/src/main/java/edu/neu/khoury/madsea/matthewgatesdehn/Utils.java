package edu.neu.khoury.madsea.matthewgatesdehn;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {

    public static int indexOfStringArray(String[] array, String key) {
        int returnvalue = -1;
        for (int i = 0; i < array.length; ++i) {
            if (key.equals(array[i])) {
                returnvalue = i;
                break;
            }
        }
        return returnvalue;
    }

    public static float convertDpToPx(int dp, Context context) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );

    }

}
