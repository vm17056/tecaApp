package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.Coleccion;
import com.sv.proye.tecaapp.models.ColeccionLibro;
import com.sv.proye.tecaapp.models.Libro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ColeccionLibroDao extends DatabaseHandler<ColeccionLibro> implements DataObjectService<ColeccionLibro> {

    private static final String TABLE_NAME = "COLECCIONLIBRO";
    private static final String ID_KEY_NAME = "IDCOLECCION";

    private final DataObjectService<Coleccion> coleccionDao;
    private final DataObjectService<Libro> libroDao;

    public ColeccionLibroDao(@Nullable Context context) {
        super(context);
        coleccionDao = new ColeccionDao(context);
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
    protected ColeccionLibro parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        ColeccionLibro entity = null;
        try {
            entity = new ColeccionLibro(
                    cursor.getInt(0),
                    libroDao.buscarModeloPodId(cursor.getInt(1)),
                    coleccionDao.buscarModeloPodId(cursor.getInt(2))
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
        keys.add(new KeysMatch("LIBRO", 1, true, null, null, null));
        keys.add(new KeysMatch("COLECCION", 1, true, null, null, null));
        return keys;
    }

    @Override
    protected List<KeysMatch> getKeysNoIdValues(ColeccionLibro model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("LIBRO", 1, true, model.getLibro().getIdLibro(), null, null));
        keys.add(new KeysMatch("COLECCION", 1, true, model.getIdColeccion().intValue(), null, null));
        return keys;
    }


    @Override
    public void almacenarModelo(ColeccionLibro entity) {
        save(getKeysNoIdValues(entity));
    }

    @Override
    public int actualizarModelo(ColeccionLibro entity) {
        return update(getKeysNoIdValues(entity), entity.getIdColeccion());
    }

    @Override
    public void eliminarModelo(Integer id) {
        delete(id);
    }

    @Override
    public List<ColeccionLibro> listarModelos() {
        return getAllModels();
    }

    @Override
    public ColeccionLibro buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }
}
