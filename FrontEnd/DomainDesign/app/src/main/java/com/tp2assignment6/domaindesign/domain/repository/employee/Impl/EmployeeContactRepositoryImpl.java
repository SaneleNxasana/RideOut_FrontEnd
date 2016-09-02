package com.tp2assignment6.domaindesign.domain.repository.employee.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp2assignment6.domaindesign.domain.conf.databases.DBConstants;
import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeContact;
import com.tp2assignment6.domaindesign.domain.repository.employee.EmployeeContactRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class EmployeeContactRepositoryImpl extends SQLiteOpenHelper implements EmployeeContactRepository{
    public static final String TABLE_NAME = "employeeContact";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "idNumber";
    public static final String COLUMN_CONTACTTYPE = "contactType";
    public static final String COLUMN_CONTACTVALUE = "contactValue";

    //Database creation!
    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CONTACTTYPE + " TEXT NOT NULL, "
            + COLUMN_CONTACTVALUE + " TEXT UNIQUE NOT NULL );";

    public EmployeeContactRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {db = this.getWritableDatabase();}

    public void close() {
        this.close();
    }

    @Override
    public EmployeeContact create(EmployeeContact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_CONTACTTYPE, entity.getContactType());
        values.put(COLUMN_CONTACTVALUE, entity.getContactValue());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        EmployeeContact insertEntity = new EmployeeContact.Builder()
                .copy(entity)
                .idNumber(new Long(id))
                .build();
        return insertEntity;
    }

    @Override
    public EmployeeContact readById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CONTACTTYPE,
                        COLUMN_CONTACTVALUE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor.moveToFirst()) {
            final EmployeeContact EmployeeContact = new EmployeeContact.Builder()
                    .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .contactType(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTTYPE)))
                    .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTVALUE)))
                    .build();
            return EmployeeContact;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<EmployeeContact> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<EmployeeContact> EmployeeContact = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final EmployeeContact setting = new EmployeeContact.Builder()
                        .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .contactType(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTTYPE)))
                        .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTVALUE)))
                        .build();
                EmployeeContact.add(setting);
            } while (cursor.moveToNext());
        }
        return EmployeeContact;
    }

    @Override
    public EmployeeContact update(EmployeeContact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_CONTACTTYPE, entity.getContactType());
        values.put(COLUMN_CONTACTVALUE, entity.getContactValue());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())}
        );
        return entity;
    }

    @Override
    public EmployeeContact delete(EmployeeContact entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())});
        return entity;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(DATABASE_CREATE);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
