package com.sv.proye.tecaapp.views.adapters.holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.sv.proye.tecaapp.R;

public abstract class TextBtnEditHolder<Entitier> extends RecyclerView.ViewHolder {

    public MaterialTextView textView;
    public MaterialButton editBtn;
    public ConstraintLayout constraintLayout;

    public TextBtnEditHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.holder_text_btn_edit_field);
        editBtn = itemView.findViewById(R.id.holder_text_btn_edit_button);
        constraintLayout = itemView.findViewById(R.id.holder_edit);
    }

    public abstract void asignarDatos(Entitier data);
}
