package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.Coleccion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ColeccionDao extends DatabaseHandler<Coleccion> implements DataObjectService<Coleccion> {

    private static final String TABLE_NAME = "COLECCION";
    private static final String ID_KEY_NAME = "IDCOLECCION";


    public ColeccionDao(@Nullable Context context) {
        super(context);
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
    protected Coleccion parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Coleccion entity = null;
        try {
            entity = new Coleccion(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
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
        keys.add(new KeysMatch("CODIGO", 2, true, null, null, null));
        keys.add(new KeysMatch("NOMBRE", 2, true, null, null, null));
        return keys;
    }

    @Override
    protected List<KeysMatch> getKeysNoIdValues(Coleccion model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("CODIGO", 2, true, null, null, model.getCodigo()));
        keys.add(new KeysMatch("NOMBRE", 2, true, null, null, model.getNombre()));
        return keys;
    }


    @Override
    public Long almacenarModelo(Coleccion entity) {
        return save(getKeysNoIdValues(entity));
    }

    @Override
    public int actualizarModelo(Coleccion entity) {
        return update(getKeysNoIdValues(entity), entity.getIdColeccion());
    }

    @Override
    public int eliminarModelo(Integer id) {
        return delete(id);
    }

    @Override
    public List<Coleccion> listarModelos() {
        return getAllModels();
    }

    @Override
    public Coleccion buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }
}
