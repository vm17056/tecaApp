package com.sv.proye.tecaapp.views.adapters.holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.sv.proye.tecaapp.R;

public abstract class TextOnlyHolder<Entitier> extends RecyclerView.ViewHolder {

    public MaterialTextView textView;
    public ConstraintLayout constraintLayout;

    public TextOnlyHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.holder_text_only);
        constraintLayout = itemView.findViewById(R.id.holder_text);
    }

    public abstract void asignarDatos(Entitier data);
}
