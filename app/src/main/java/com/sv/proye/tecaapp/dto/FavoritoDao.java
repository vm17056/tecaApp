package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.Favorito;
import com.sv.proye.tecaapp.models.HistorialLeido;
import com.sv.proye.tecaapp.models.Libro;
import com.sv.proye.tecaapp.models.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FavoritoDao extends DatabaseHandler<Favorito> implements DataObjectService<Favorito> {

    private static final String TABLE_NAME = "FAVORITO";
    private static final String ID_KEY_NAME = "IDFAVORITO";

    private DataObjectService<Usuario> usuarioDao;
    private DataObjectService<Libro> libroDao;

    public FavoritoDao(@Nullable Context context) {
        super(context);
        usuarioDao = new UsuarioDao(context);
        libroDao = new LibroDao(context);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getIdKey() {
        return ID_KEY_NAME;
    }

    @Override
    protected Favorito parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Favorito entity = null;
        try {
            entity = new Favorito(
                    cursor.getInt(0),
                    usuarioDao.buscarModeloPodId(cursor.getInt(1)),
                    libroDao.buscarModeloPodId(cursor.getInt(2))
            );
        } catch (Exception ignore) {
        }
        return entity;
    }

    /**
     * @return List<KeysMatch>
     * KeysMatch ( name , type , notnull, intValue , realValue , textValue )
     * type
     * 1 is Integer is INTEGER
     * 2 is String is TEXT
     * 3 is Double is REAL
     * 4 is Boolean is INTEGER
     * 5 is Date is TEXT
     */
    @Override
    protected List<KeysMatch> getKeysNoId() {
        List<KeysMatch> keys = new ArrayList<>();
        keys.add(new KeysMatch("USUARIO", 1, true, null, null, null));
        keys.add(new KeysMatch("LIBRO", 1, true, null, null, null));
        return keys;
    }

    @Override
    protected List<KeysMatch> getKeysNoIdValues(Favorito model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("USUARIO", 1, true, model.getUsuario().getIdUsuario(), null, null));
        keys.add(new KeysMatch("LIBRO", 1, true, model.getLibro().getIdLibro(), null, null));
        return keys;
    }


    @Override
    public Long almacenarModelo(Favorito entity) {
        return save(getKeysNoIdValues(entity));
    }

    @Override
    public int actualizarModelo(Favorito entity) {
        return update(getKeysNoIdValues(entity), entity.getIdFavorito());
    }

    @Override
    public int eliminarModelo(Integer id) {
        return delete(id);
    }

    @Override
    public List<Favorito> listarModelos() {
        return getAllModels();
    }

    @Override
    public Favorito buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }

    public Favorito buscarPorUsuarioAndLibro(Integer idUsuario, Integer idLibro) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(getTableName(), prepareKeysSelection(), " USUARIO = ? AND LIBRO = ? ",
                new String[]{String.valueOf(idUsuario), String.valueOf(idLibro)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Favorito model = parseCursorToModel(cursor);
        db.close();
        // return the generic model
        return model;
    }
}
