package com.sv.proye.tecaapp.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.views.adapters.holders.TextOnlyHolder;

import java.util.List;

public abstract class AbsTextOnlyReciclerAdapter<Autoxx> extends RecyclerView.Adapter<TextOnlyHolder<Autoxx>> {

    private final List<Autoxx> items;
    private final Context context;
    private final FragmentActivity fragmentActivity;

    protected MaterialTextView materialTextView;

    public AbsTextOnlyReciclerAdapter(List<Autoxx> items, Context context, FragmentActivity fragmentActivity) {
        this.items = items;
        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public TextOnlyHolder<Autoxx> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_text_only, parent, false);
        return new TextOnlyHolder<Autoxx>(v) {
            @Override
            public void asignarDatos(Autoxx data) {
                materialTextView = textView;
                asignarDatillos(data);
            }
        };
    }

    public abstract void asignarDatillos(Autoxx data);

    @Override
    public void onBindViewHolder(@NonNull TextOnlyHolder<Autoxx> holder, int position) {
        holder.asignarDatos(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

}
