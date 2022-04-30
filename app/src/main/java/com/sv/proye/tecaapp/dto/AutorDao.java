package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.Autor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AutorDao extends DatabaseHandler<Autor> implements DataObjectService<Autor> {

    private static final String TABLE_NAME = "AUTOR";
    private static final String ID_KEY_NAME = "IDAUTOR";

    public AutorDao(@Nullable Context context) {
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
    protected Autor parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Autor entity = null;
        try {
            entity = new Autor(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    simpleDateFormat.parse(cursor.getString(3)),
                    cursor.getInt(4) == 1,
                    simpleDateFormat.parse(cursor.getString(5))
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
        keys.add(new KeysMatch("NOMBRE", 2, true, null, null, null));
        keys.add(new KeysMatch("APELLIDO", 2, true, null, null, null));
        keys.add(new KeysMatch("FECHANACIMIENTO", 2, true, null, null, null));
        keys.add(new KeysMatch("FALLECIDO", 4, true, null, null, null));
        keys.add(new KeysMatch("FECHADEFUNCION", 5, true, null, null, null));
        return keys;
    }

    @Override
    protected List<KeysMatch> getKeysNoIdValues(Autor model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("NOMBRE", 2, true, null, null, model.getNombre()));
        keys.add(new KeysMatch("APELLIDO", 2, true, null, null, model.getApellido()));
        keys.add(new KeysMatch("FECHANACIMIENTO", 2, true, null, null, simpleDateFormat.format(model.getFechaNacimiento())));
        keys.add(new KeysMatch("FALLECIDO", 4, true, model.getFallecido() ? 1 : 0, null, null));
        keys.add(new KeysMatch("FECHADEFUNCION", 5, true, null, null, simpleDateFormat.format(model.getFechaDefuncion())));
        return keys;
    }


    @Override
    public Long almacenarModelo(Autor entity) {
        return save(getKeysNoIdValues(entity));
    }

    @Override
    public int actualizarModelo(Autor entity) {
        return update(getKeysNoIdValues(entity), entity.getIdAutor());
    }

    @Override
    public int eliminarModelo(Integer id) {
        return delete(id);
    }

    @Override
    public List<Autor> listarModelos() {
        return getAllModels();
    }

    @Override
    public Autor buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }
}
