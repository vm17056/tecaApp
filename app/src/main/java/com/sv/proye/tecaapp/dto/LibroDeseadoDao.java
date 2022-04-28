package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.LibroDeseado;
import com.sv.proye.tecaapp.models.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LibroDeseadoDao extends DatabaseHandler<LibroDeseado> implements DataObjectService<LibroDeseado> {

    private static final String TABLE_NAME = "LIBRODESEADO";
    private static final String ID_KEY_NAME = "IDLIBRODES";

    private DataObjectService<Usuario> usuarioDao;

    public LibroDeseadoDao(@Nullable Context context) {
        super(context);
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
    protected LibroDeseado parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        LibroDeseado entity = null;
        try {
            entity = new LibroDeseado(
                    cursor.getInt(0),
                    usuarioDao.buscarModeloPodId(cursor.getInt(1)),
                    cursor.getString(2),
                    cursor.getString(3)
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
        keys.add(new KeysMatch("CODIGO", 2, false, null, null, null));
        keys.add(new KeysMatch("NOMBRE", 2, true, null, null, null));
        return keys;
    }

    @Override
    protected List<KeysMatch> getKeysNoIdValues(LibroDeseado model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("USUARIO", 1, true, model.getUsuario().getIdUsuario(), null, null));
        keys.add(new KeysMatch("CODIGO", 2, false, null, null, model.getCodigo()));
        keys.add(new KeysMatch("NOMBRE", 2, true, null, null, model.getNombre()));
        return keys;
    }


    @Override
    public void almacenarModelo(LibroDeseado entity) {
        save(getKeysNoIdValues(entity));
    }

    @Override
    public int actualizarModelo(LibroDeseado entity) {
        return update(getKeysNoIdValues(entity), entity.getIdLibroDes());
    }

    @Override
    public void eliminarModelo(Integer id) {
        delete(id);
    }

    @Override
    public List<LibroDeseado> listarModelos() {
        return getAllModels();
    }

    @Override
    public LibroDeseado buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }
}
