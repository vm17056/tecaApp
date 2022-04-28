package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.HistorialLeido;
import com.sv.proye.tecaapp.models.Libro;
import com.sv.proye.tecaapp.models.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HistorialLeidoDao extends DatabaseHandler<HistorialLeido> implements DataObjectService<HistorialLeido> {

    private static final String TABLE_NAME = "HISTORIALLEIDO";
    private static final String ID_KEY_NAME = "IDHISTORIAL";

    private DataObjectService<Usuario> usuarioDao;
    private DataObjectService<Libro> libroDao;

    public HistorialLeidoDao(@Nullable Context context) {
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
    protected HistorialLeido parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        HistorialLeido entity = null;
        try {
            entity = new HistorialLeido(
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
    protected List<KeysMatch> getKeysNoIdValues(HistorialLeido model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("USUARIO", 1, true, model.getUsuario().getIdUsuario(), null, null));
        keys.add(new KeysMatch("LIBRO", 1, true, model.getLibro().getIdLibro(), null, null));
        return keys;
    }


    @Override
    public void almacenarModelo(HistorialLeido entity) {
        save(getKeysNoIdValues(entity));
    }

    @Override
    public int actualizarModelo(HistorialLeido entity) {
        return update(getKeysNoIdValues(entity), entity.getIdHistorial());
    }

    @Override
    public void eliminarModelo(Integer id) {
        delete(id);
    }

    @Override
    public List<HistorialLeido> listarModelos() {
        return getAllModels();
    }

    @Override
    public HistorialLeido buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }
}