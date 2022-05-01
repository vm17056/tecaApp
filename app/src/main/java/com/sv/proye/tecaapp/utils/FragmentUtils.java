package com.sv.proye.tecaapp.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.sv.proye.tecaapp.R;


public class FragmentUtils {
    public static void showFragmentGlobal(FragmentActivity activity, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_menu_inicio, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public static void showFragmentGlobal(FragmentActivity fragmentActivity, Fragment fragment, @NonNull Integer id) {
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(id, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
