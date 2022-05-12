package com.sv.proye.tecaapp.views.adapters.holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.models.Inventario;

public abstract class ListaLibrosHolder extends RecyclerView.ViewHolder {

    public MaterialTextView textView;
    public ShapeableImageView favoritoBtn;
    public ShapeableImageView leidoBtn;
    public ConstraintLayout constraintLayout;

    public ListaLibrosHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.holder_listar_libros_text);
        favoritoBtn = itemView.findViewById(R.id.holder_listar_libros_favorito);
        leidoBtn = itemView.findViewById(R.id.holder_listar_libros_leido);
        constraintLayout = itemView.findViewById(R.id.holder_listar_libros_edit);
    }

    public abstract void asignarDatos(Inventario data);
}
