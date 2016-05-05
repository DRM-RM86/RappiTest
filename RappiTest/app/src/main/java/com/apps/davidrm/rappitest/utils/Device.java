package com.apps.davidrm.rappitest.utils;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Created by DRM on 02/05/16.
 */
public class Device {
    private static double MIN_INCHES_TABLET = 7.5;
    public static boolean isTablet(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        return diagonalInches>= MIN_INCHES_TABLET;
    }
    public static Point getSizeScreen(Context context) {
        Point point = new Point();
        try {DisplayMetrics display = context.getResources().getDisplayMetrics();
            point.x = display.widthPixels;;
            point.y = display.heightPixels;
        } catch (Exception e) {}
        return point;
    }

    public static  boolean isLollipop(){
         return  Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }


    public static boolean isConnected(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (isLollipop()) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        }else {
            if (connectivityManager != null) {
                //noinspection deprecation
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isLandscapeDevice(Context context)
    {
        boolean isLandscapeDevice = false;

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int rotation = getRotation(context);
        if ((rotation == 0) || (rotation == 180)) {
            isLandscapeDevice = dm.widthPixels > dm.heightPixels;
        } else {
            isLandscapeDevice = dm.widthPixels < dm.heightPixels;
        }
        return isLandscapeDevice;
    }

    public static int getRotation(Context context)
    {
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

        int orientation = windowManager.getDefaultDisplay().getRotation();
        int ret = -1;

        switch (orientation) {
            case Surface.ROTATION_0:
                ret = 0;
                break;

            case Surface.ROTATION_90:
                ret = 90;
                break;

            case Surface.ROTATION_180:
                ret = 180;
                break;

            case Surface.ROTATION_270:
                ret = 270;
        }

        return ret;
    }
}
