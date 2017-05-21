package com.cxing.mygank.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by zhao on 17-5-21.
 */

public class ToastUtil {
    private static Toast sToast;

    public static void showToast(Context context , String msg, int duration){
        if(context==null || TextUtils.isEmpty(msg)){
            return;
        }
        if(duration!=0 || duration !=1){
            duration= Toast.LENGTH_SHORT;
        }
        if(sToast==null){
            sToast = Toast.makeText(context,msg, duration);
        }else{
            sToast.setText(msg);
            sToast.setDuration(duration);
        }
        sToast.show();
    }
}
