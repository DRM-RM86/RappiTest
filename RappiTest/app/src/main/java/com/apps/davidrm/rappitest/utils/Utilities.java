package com.apps.davidrm.rappitest.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;

import com.apps.davidrm.rappitest.R;

/**
 * Created by DRM on 04/05/16.
 */
public class Utilities {

    public static void createAlert(final Activity activity, String mensaje) {
        Utilities.createAlert(activity, null, mensaje);
    }

    public static void createAlert(final Activity activity, String mensaje, DialogInterface.OnClickListener ocl) {
        Utilities.createAlert(activity, null, mensaje, ocl);
    }

    public static void createAlert(final Activity activity, String titulo, String mensaje) {
        Utilities.createAlert(activity, titulo, mensaje, null);
    }

    public static void createAlert(final Activity activity, String titulo, String mensaje, DialogInterface.OnClickListener ocl) {
        Utilities.createAlert(activity, titulo, mensaje, false, ocl);
    }

    public static void createAlert(final Activity activity, String titulo, String mensaje, boolean cancelButton, DialogInterface.OnClickListener ocl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.AppTheme));
        builder.setTitle(titulo)
                .setMessage(mensaje)
                .setCancelable(true)
                .setPositiveButton("OK", ocl);
        if(cancelButton) {
            builder.setNegativeButton("Cancelar", null);
        }
        AlertDialog alert = builder.create();
        alert.show();
    }
}
