package com.sv.proye.tecaapp.dto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sv.proye.tecaapp.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseHandler<Modelo> extends SQLiteOpenHelper {
    private static final Integer DB_VERSION = 2;
    private static final String DB_NAME = "BIBLIOTECAR.db";
    protected static final String DATE_FORMAT = DateUtils.FORMAT_YYYY_MM_DD;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DatabaseHandler(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    protected abstract String getTableName();

    protected abstract String getIdKey();

    protected abstract Modelo parseCursorToModel(Cursor cursor);

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
    protected abstract List<KeysMatch> getKeysNoId();

    protected abstract List<KeysMatch> getKeysNoIdValues(Modelo model);

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + getTableName() + "("
                + getIdKey() + " INTEGER PRIMARY KEY," + prepareKeysTableDDL(getKeysNoId()) + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        verifyTable(db);
    }

    private void verifyTable(SQLiteDatabase db) {
        String verifyOpened = "Verify for " + getTableName() + " has ben opened.";
        System.out.println(verifyOpened);
        String CREATE_TABLE_IF_NOT_EXIST = "CREATE TABLE IF NOT EXISTS " + getTableName() + "("
                + getIdKey() + " INTEGER PRIMARY KEY," + prepareKeysTableDDL(getKeysNoId()) + ")";
        db.execSQL(CREATE_TABLE_IF_NOT_EXIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + getTableName());
        // Create tables again
        onCreate(db);
    }

    private String prepareKeysTableDDL(List<KeysMatch> keys) {
        StringBuilder dDLQuery = new StringBuilder("");
        int i = 0;
        for (KeysMatch key : keys) {
            if (i > 0) dDLQuery.append(",");
            i++;
            dDLQuery.append(key.getKeyName());
            dDLQuery.append(" ");
            dDLQuery.append(key.getDataTypeMatch());
            if (key.getNotNull()) {
                dDLQuery.append(" ");
                dDLQuery.append("NOT NULL");
            }
        }
        return dDLQuery.toString();
    }

    protected String[] prepareKeysSelection() {
        StringBuilder dDLQuery = new StringBuilder("");
        int i = 0;
        List<String> keysAndId = new ArrayList<>();
        keysAndId.add(getIdKey());
        for (KeysMatch key : getKeysNoId()) {
            keysAndId.add(key.getKeyName());
        }
        return keysAndId.toArray(new String[0]);
    }

    /**
     * agregar un nuevo registro a la tabla de la base de datos
     *
     * @param model is List<KeysMatch>
     * @return number row ID or -1 if error
     */
    protected Long save(List<KeysMatch> model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (KeysMatch key : model) {
            switch (key.getDataTypeMatch()) {
                case "INTEGER":
                    values.put(key.getKeyName(), key.getIntValue());
                    break;
                case "TEXT":
                    values.put(key.getKeyName(), key.getTextValue());
                    break;
                case "REAL":
                    values.put(key.getKeyName(), key.getRealValue());
                    break;
            }
        }
        // guardando Row
        Long saved = db.insert(getTableName(), null, values);
        db.close();

        return saved;
    }

    /**
     * @return number of rows affected
     */
    protected int update(List<KeysMatch> model, Integer idValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (KeysMatch key : model) {
            switch (key.getDataTypeMatch()) {
                case "INTEGER":
                    values.put(key.getKeyName(), key.getIntValue());
                    break;
                case "TEXT":
                    values.put(key.getKeyName(), key.getTextValue());
                    break;
                case "REAL":
                    values.put(key.getKeyName(), key.getRealValue());
                    break;
            }
        }
        // updating row
        int updated = db.update(getTableName(), values, getIdKey() + " = ?",
                new String[]{idValue.toString()});
        db.close();
        return updated;
    }

    /**
     * @return number of rows affected
     */
    protected int delete(Integer idValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer rowsAffected = db.delete(getTableName(), getIdKey() + " = ?",
                new String[]{idValue.toString()});
        db.close();
        return rowsAffected;
    }

    protected Modelo getSingleModelById(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(getTableName(), prepareKeysSelection(), getIdKey() + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Modelo model = parseCursorToModel(cursor);
        db.close();
        // return the generic model
        return model;
    }

    protected List<Modelo> getAllModels() {
        List<Modelo> modelList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + getTableName();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Modelo model = parseCursorToModel(cursor);
                // Adding model to list
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        // return model list whit rows found
        db.close();
        return modelList;
    }


}
