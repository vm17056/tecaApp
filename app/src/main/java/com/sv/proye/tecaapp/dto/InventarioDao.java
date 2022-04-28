package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.Inventario;
import com.sv.proye.tecaapp.models.Libro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InventarioDao extends DatabaseHandler<Inventario> implements DataObjectService<Inventario> {

    private static final String TABLE_NAME = "INVENTARIO";
    private static final String ID_KEY_NAME = "IDINVENTARIO";

    private DataObjectService<Libro> libroDao;

    public InventarioDao(@Nullable Context context) {
        super(context);
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
    protected Inventario parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Inventario entity = null;
        try {
            entity = new Inventario(
                    cursor.getInt(0),
                    libroDao.buscarModeloPodId(cursor.getInt(1)),
                    cursor.getInt(2),
                    simpleDateFormat.parse(cursor.getString(3))
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
        keys.add(new KeysMatch("CANTIDAD", 1, true, null, null, null));
        keys.add(new KeysMatch("FECHAACTUALIZADO", 2, true, null, null, null));
        return keys;
    }

    @Override
    protected List<KeysMatch> getKeysNoIdValues(Inventario model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("LIBRO", 1, true, model.getLibro().getIdLibro(), null, null));
        keys.add(new KeysMatch("CANTIDAD", 1, true, model.getCantidad(), null, null));
        keys.add(new KeysMatch("FECHAACTUALIZADO", 2, true, null, null, simpleDateFormat.format(model.getFechaActualizado())));
        return keys;
    }


    @Override
    public void almacenarModelo(Inventario entity) {
        save(getKeysNoIdValues(entity));
    }

    @Override
    public int actualizarModelo(Inventario entity) {
        return update(getKeysNoIdValues(entity), entity.getIdInventario());
    }

    @Override
    public void eliminarModelo(Integer id) {
        delete(id);
    }

    @Override
    public List<Inventario> listarModelos() {
        return getAllModels();
    }

    @Override
    public Inventario buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }
}
