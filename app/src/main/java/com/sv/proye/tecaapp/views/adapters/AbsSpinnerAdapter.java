package com.sv.proye.tecaapp.views.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AbsSpinnerAdapter<Modelo> extends BaseAdapter {

    private final List<Modelo> items;

    public AbsSpinnerAdapter(List<Modelo> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Modelo getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return absView(position, convertView, parent);
    }

    public abstract View absView(int position, View convertView, ViewGroup parent);


}
