package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.Inventario;
import com.sv.proye.tecaapp.models.Prestamo;
import com.sv.proye.tecaapp.models.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDao extends DatabaseHandler<Prestamo> implements DataObjectService<Prestamo> {

    private static final String TABLE_NAME = "PRESTAMO";
    private static final String ID_KEY_NAME = "IDPRESTAMO";

    private final DataObjectService<Inventario> inventarioDao;
    private final DataObjectService<Usuario> usuarioDao;

    public PrestamoDao(@Nullable Context context) {
        super(context);
        inventarioDao = new InventarioDao(context);
        usuarioDao = new UsuarioDao(context);
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
    protected Prestamo parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Prestamo entity = null;
        try {
            entity = new Prestamo(
                    cursor.getInt(0),
                    inventarioDao.buscarModeloPodId(cursor.getInt(1)),
                    usuarioDao.buscarModeloPodId(cursor.getInt(2)),
                    cursor.getInt(3),
                    simpleDateFormat.parse(cursor.getString(4)),
                    simpleDateFormat.parse(cursor.getString(5)),
                    cursor.getInt(6) == 1
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
        keys.add(new KeysMatch("USUARIO", 1, true, null, null, null));
        keys.add(new KeysMatch("CANTIDAD", 1, true, null, null, null));
        keys.add(new KeysMatch("fechaPrestamo", 2, true, null, null, null));
        keys.add(new KeysMatch("fechaEntrega", 2, true, null, null, null));
        keys.add(new KeysMatch("devuelto", 4, true, null, null, null));
        return keys;
    }

    @Override
    protected List<KeysMatch> getKeysNoIdValues(Prestamo model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("INVENTARIO", 1, true, model.getInventario().getIdInventario(), null, null));
        keys.add(new KeysMatch("USUARIO", 1, true, model.getUsuario().getIdUsuario(), null, null));
        keys.add(new KeysMatch("CANTIDAD", 1, true, model.getCantidad(), null, null));
        keys.add(new KeysMatch("fechaPrestamo", 2, true, null, null, simpleDateFormat.format(model.getFechaPrestamo())));
        keys.add(new KeysMatch("fechaEntrega", 2, true, null, null, simpleDateFormat.format(model.getFechaEntrega())));
        keys.add(new KeysMatch("devuelto", 4, true, model.getDevuelto() ? 1 : 0, null, null));
        return keys;
    }


    @Override
    public Long almacenarModelo(Prestamo entity) {
        return save(getKeysNoIdValues(entity));
    }

    @Override
    public int actualizarModelo(Prestamo entity) {
        return update(getKeysNoIdValues(entity), entity.getIdPrestamo());
    }

    @Override
    public int eliminarModelo(Integer id) {
        return delete(id);
    }

    @Override
    public List<Prestamo> listarModelos() {
        return getAllModels();
    }

    @Override
    public Prestamo buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }
}
