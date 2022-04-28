package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.Compra;
import com.sv.proye.tecaapp.models.Inventario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CompraDao extends DatabaseHandler<Compra> implements DataObjectService<Compra> {

    private static final String TABLE_NAME = "COMPRA";
    private static final String ID_KEY_NAME = "IDCOMPRA";

    private final DataObjectService<Inventario> inventarioDao;

    public CompraDao(@Nullable Context context) {
        super(context);
        inventarioDao = new InventarioDao(context);
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
    protected Compra parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Compra entity = null;
        try {
            entity = new Compra(
                    cursor.getInt(0),
                    inventarioDao.buscarModeloPodId(cursor.getInt(1)),
                    cursor.getInt(2),
                    simpleDateFormat.parse(cursor.getString(3)),
                    cursor.getDouble(4),
                    cursor.getString(5)
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
        keys.add(new KeysMatch("INVENTARIO", 1, true, null, null, null));
        keys.add(new KeysMatch("CANTIDAD", 1, true, null, null, null));
        keys.add(new KeysMatch("FECHACOMPRA", 2, true, null, null, null));
        keys.add(new KeysMatch("PRECIOUNITARIO", 3, true, null, null, null));
        keys.add(new KeysMatch("CODIGOCOMPRA", 2, true, null, null, null));
        return keys;
    }

    @Override
    protected List<KeysMatch> getKeysNoIdValues(Compra model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("INVENTARIO", 1, true, model.getInventario().getCantidad(), null, null));
        keys.add(new KeysMatch("CANTIDAD", 1, true, model.getCantidad(), null, null));
        keys.add(new KeysMatch("FECHACOMPRA", 2, true, null, null, simpleDateFormat.format(model.getFechaCompra())));
        keys.add(new KeysMatch("PRECIOUNITARIO", 3, true, null, model.getPrecioUnitario(), null));
        keys.add(new KeysMatch("CODIGOCOMPRA", 2, true, null, null, model.getCodigoCompra()));
        return keys;
    }


    @Override
    public void almacenarModelo(Compra entity) {
        save(getKeysNoIdValues(entity));
    }

    @Override
    public int actualizarModelo(Compra entity) {
        return update(getKeysNoIdValues(entity), entity.getIdCompra());
    }

    @Override
    public void eliminarModelo(Integer id) {
        delete(id);
    }

    @Override
    public List<Compra> listarModelos() {
        return getAllModels();
    }

    @Override
    public Compra buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }
}
