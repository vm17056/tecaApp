package com.sv.proye.tecaapp.dto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.models.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends DatabaseHandler<Usuario> implements DataObjectService<Usuario> {

    private static final String TABLE_NAME = "USUARIO";
    private static final String ID_KEY_NAME = "IDUSUARIO";

    public UsuarioDao(@Nullable Context context) {
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
    protected Usuario parseCursorToModel(Cursor cursor) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Usuario entity = null;
        try {
            entity = new Usuario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    simpleDateFormat.parse(cursor.getString(5)),
                    cursor.getString(6),
                    cursor.getString(7)
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
        keys.add(new KeysMatch("USERNAME", 2, true, null, null, null));
        keys.add(new KeysMatch("USERPASS", 2, true, null, null, null));
        keys.add(new KeysMatch("FECHANACIMIENTO", 2, true, null, null, null));
        keys.add(new KeysMatch("TELEFONO", 2, true, null, null, null));
        keys.add(new KeysMatch("EMAIL", 2, true, null, null, null));
        return keys;
    }

    @Override
    protected List<KeysMatch> getKeysNoIdValues(Usuario model) {
        List<KeysMatch> keys = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        keys.add(new KeysMatch("NOMBRE", 2, true, null, null, model.getNombre()));
        keys.add(new KeysMatch("APELLIDO", 2, true, null, null, model.getApellido()));
        keys.add(new KeysMatch("USERNAME", 2, true, null, null, model.getUserName()));
        keys.add(new KeysMatch("USERPASS", 2, true, null, null, model.getUserPass()));
        keys.add(new KeysMatch("FECHANACIMIENTO", 2, true, null, null, simpleDateFormat.format(model.getFechaNacimiento())));
        keys.add(new KeysMatch("TELEFONO", 2, true, null, null, model.getTelefono()));
        keys.add(new KeysMatch("EMAIL", 2, true, null, null, model.getEmail()));
        return keys;
    }


    @Override
    public void almacenarModelo(Usuario usuario) {
        save(getKeysNoIdValues(usuario));
    }

    @Override
    public int actualizarModelo(Usuario usuario) {
        return update(getKeysNoIdValues(usuario), usuario.getIdUsuario());
    }

    @Override
    public void eliminarModelo(Integer id) {
        delete(id);
    }

    @Override
    public List<Usuario> listarModelos() {
        return getAllModels();
    }

    @Override
    public Usuario buscarModeloPodId(Integer id) {
        return getSingleModelById(id);
    }

    public Usuario validarUsuario(Usuario usuario) {
        Usuario usuariox = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(getTableName(), prepareKeysSelection(), " USERNAME = ? AND USERPASS = ? ",
                new String[]{usuario.getUserName(), usuario.getUserPass()}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            usuariox = parseCursorToModel(cursor);
        }
        db.close();
        return usuariox;
    }
}
