package com.sv.proye.tecaapp.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.views.adapters.holders.TextBtnEditHolder;

import java.util.List;

public abstract class AbsTextBtnEditReciclerAdapter<Elenty> extends RecyclerView.Adapter<TextBtnEditHolder<Elenty>> {

    private final List<Elenty> items;
    private final Context context;
    private final FragmentManager fragmentManager;

    protected MaterialTextView materialTextView;
    public MaterialButton materialButton;
    public ConstraintLayout layout;
    public TextBtnEditHolder<Elenty> viewHolder = null;

    public AbsTextBtnEditReciclerAdapter(List<Elenty> items, Context context, FragmentManager fragmentManager) {
        this.items = items;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public TextBtnEditHolder<Elenty> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_text_btn_edit, parent, false);
        viewHolder = new TextBtnEditHolder<Elenty>(v) {
            @Override
            public void asignarDatos(Elenty data) {
                materialTextView = textView;
                layout = constraintLayout;
                materialButton = editBtn;
                asignarDatillos(data);
            }
        };
        return viewHolder;
    }

    public abstract void asignarDatillos(Elenty data);

    @Override
    public void onBindViewHolder(@NonNull TextBtnEditHolder<Elenty> holder, int position) {
        holder.asignarDatos(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

}
