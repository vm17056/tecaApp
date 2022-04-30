package com.sv.proye.tecaapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sv.proye.tecaapp.R;

public class MessageUtils {

    public static void displaySuccess(String message, Context context) {
        final Dialog dialog1 = new MaterialAlertDialogBuilder(context)
                .setView(R.layout.toast_succes)
                .setCancelable(true)
                .create();
        dialog1.show();
        TextView textView = dialog1.findViewById(R.id.text);
        if (textView != null) textView.setText(message);

    }

    public static void displayFail(String message, Context context) {
        final Dialog dialog1 = new MaterialAlertDialogBuilder(context)
                .setView(R.layout.toast_fail)
                .setCancelable(true)
                .create();
        dialog1.show();
        TextView textView = dialog1.findViewById(R.id.text);
        if (textView != null) textView.setText(message);
    }

    public static void displayWarming(String message, Context context) {
        final Dialog dialog1 = new MaterialAlertDialogBuilder(context)
                .setView(R.layout.toast_warm)
                .setCancelable(true)
                .create();
        dialog1.show();
        TextView textView = dialog1.findViewById(R.id.text);
        if (textView != null) textView.setText(message);
    }
}
