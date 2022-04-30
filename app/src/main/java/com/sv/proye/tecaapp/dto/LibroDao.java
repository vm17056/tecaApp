package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.Autor;
import com.sv.proye.tecaapp.models.Libro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LibroDao extends DatabaseHandler<Libro> implements DataObjectService<Libro> {

    private static final String TABLE_NAME = "LIBRO";
    private static final String ID_KEY_NAME = "IDLIBRO";

    private DataObjectService<Autor> autorDao;

    public LibroDao(@Nullable Context context) {
        super(context);
        autorDao = new AutorDao(context);
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
    protected Libro parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Libro entity = null;
        try {
            entity = new Libro(
                    cursor.getInt(0),
                    autorDao.buscarModeloPodId(cursor.getInt(1)),
                    cursor.getString(2),
                    cursor.getString(3),
                    simpleDateFormat.parse(cursor.getString(4))
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
        keys.add(new KeysMatch("AUTOR", 1, true, null, null, null));
        keys.add(new KeysMatch("CODIGO", 2, true, null, null, null));
        keys.add(new KeysMatch("NOMBRE", 2, true, null, null, null));
        keys.add(new KeysMatch("FECHAPUBLICACION", 5, true, null, null, null));
        return keys;
    }

    @Override
    protected List<KeysMatch> getKeysNoIdValues(Libro model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("AUTOR", 1, true, model.getAutor().getIdAutor(), null, null));
        keys.add(new KeysMatch("CODIGO", 2, true, null, null, model.getCodigo()));
        keys.add(new KeysMatch("NOMBRE", 2, true, null, null, model.getNombre()));
        keys.add(new KeysMatch("FECHAPUBLICACION", 5, true, null, null, simpleDateFormat.format(model.getFechaPublicacion())));
        return keys;
    }


    @Override
    public Long almacenarModelo(Libro entity) {
        return save(getKeysNoIdValues(entity));
    }

    @Override
    public int actualizarModelo(Libro entity) {
        return update(getKeysNoIdValues(entity), entity.getIdLibro());
    }

    @Override
    public int eliminarModelo(Integer id) {
        return delete(id);
    }

    @Override
    public List<Libro> listarModelos() {
        return getAllModels();
    }

    @Override
    public Libro buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }
}
