package com.sv.proye.tecaapp.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.FavoritoDao;
import com.sv.proye.tecaapp.dto.HistorialLeidoDao;
import com.sv.proye.tecaapp.models.Favorito;
import com.sv.proye.tecaapp.models.HistorialLeido;
import com.sv.proye.tecaapp.models.Inventario;
import com.sv.proye.tecaapp.utils.StaticUtils;
import com.sv.proye.tecaapp.views.adapters.holders.ListaLibrosHolder;

import java.util.List;

public class ListaLibrosReciclerAdapter extends RecyclerView.Adapter<ListaLibrosHolder> {

    private final List<Inventario> items;
    private HistorialLeidoDao historialLeidoDao;
    private FavoritoDao favoritoDao;

    public ListaLibrosReciclerAdapter(List<Inventario> items, HistorialLeidoDao historialLeidoDao, FavoritoDao favoritoDao) {
        this.items = items;
        this.historialLeidoDao = historialLeidoDao;
        this.favoritoDao = favoritoDao;
    }

    @NonNull
    @Override
    public ListaLibrosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_lista_libros, parent, false);
        return new ListaLibrosHolder(v) {
            @Override
            public void asignarDatos(Inventario data) {
                String nombre = data.getLibro().getNombre() + " - " + data.getLibro().getAutor().getNombre() + " # " + data.getCantidad();
                textView.setText(nombre);
                HistorialLeido leido = historialLeidoDao.buscarPorUsuarioAndLibro(StaticUtils.usuario.getIdUsuario(), data.getLibro().getIdLibro());
                if (leido == null) {
                    leidoBtn.setImageResource(R.drawable.ic_eye_off);
                    leidoBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HistorialLeido historialLeido = new HistorialLeido();
                            historialLeido.setLibro(data.getLibro());
                            historialLeido.setUsuario(StaticUtils.usuario);
                            historialLeidoDao.almacenarModelo(historialLeido);
                            leidoBtn.setImageResource(R.drawable.ic_eye);
                        }
                    });
                }
                Favorito favorito = favoritoDao.buscarPorUsuarioAndLibro(StaticUtils.usuario.getIdUsuario(), data.getLibro().getIdLibro());
                if (favorito == null) {
                    favoritoBtn.setImageResource(R.drawable.ic_star_off);
                    favoritoBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Favorito favorito = new Favorito();
                            favorito.setLibro(data.getLibro());
                            favorito.setUsuario(StaticUtils.usuario);
                            favoritoDao.almacenarModelo(favorito);
                            favoritoBtn.setImageResource(R.drawable.ic_star);
                        }
                    });
                }
            }
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ListaLibrosHolder holder, int position) {
        holder.asignarDatos(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

}
