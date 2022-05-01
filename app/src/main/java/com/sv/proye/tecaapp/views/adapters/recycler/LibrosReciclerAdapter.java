package com.sv.proye.tecaapp.views.adapters.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.models.Libro;
import com.sv.proye.tecaapp.views.adapters.holders.TextOnlyHolder;

import java.util.List;

public class LibrosReciclerAdapter extends RecyclerView.Adapter<TextOnlyHolder<Libro>> {

    private final List<Libro> items;
    private final Context context;
    private final FragmentActivity fragmentActivity;

    public LibrosReciclerAdapter(List<Libro> items, Context context, FragmentActivity fragmentActivity) {
        this.items = items;
        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public TextOnlyHolder<Libro> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_text_only, parent, false);
        return new TextOnlyHolder<Libro>(v) {
            @Override
            public void asignarDatos(Libro data) {
                textView.setText(data.getNombre());
            }
        };
    }

    @Override
    public void onBindViewHolder(@NonNull TextOnlyHolder<Libro> holder, int position) {
        holder.asignarDatos(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

}
