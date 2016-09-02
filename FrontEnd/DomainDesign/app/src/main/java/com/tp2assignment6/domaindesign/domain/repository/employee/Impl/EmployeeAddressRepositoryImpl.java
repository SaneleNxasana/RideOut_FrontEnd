package com.tp2assignment6.domaindesign.domain.repository.employee.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp2assignment6.domaindesign.domain.conf.databases.DBConstants;
import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeAddress;
import com.tp2assignment6.domaindesign.domain.repository.employee.EmployeeAddressRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class EmployeeAddressRepositoryImpl extends SQLiteOpenHelper implements EmployeeAddressRepository {
    public static final String TABLE_NAME = "employeeAddress";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "idNumber";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_POSTALCODE = "postalCode";

    //Database creation!
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DESCRIPTION +  " TEXT NOT NULL, "
            + COLUMN_POSTALCODE + " TEXT NOT NULL );";

    public EmployeeAddressRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException{db = this.getWritableDatabase();}

    public void close(){this.close();}

    @Override
    public EmployeeAddress create(EmployeeAddress entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_POSTALCODE, entity.getPostalCode());
        long idNumber = db.insertOrThrow(TABLE_NAME, null, values);
        EmployeeAddress insertEntity = new EmployeeAddress.Builder()
                .copy(entity)
                .idNumber(new Long(idNumber))
                .build();
        return insertEntity;
    }

    @Override
    public EmployeeAddress readById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_DESCRIPTION,
                        COLUMN_POSTALCODE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()){
            final EmployeeAddress EmployeeAddress = new EmployeeAddress.Builder()
                    .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                    .build();
            return EmployeeAddress;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<EmployeeAddress> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<EmployeeAddress> EmployeeAddress = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                final EmployeeAddress setting = new EmployeeAddress.Builder()
                        .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                        .build();
                EmployeeAddress.add(setting);
            }while (cursor.moveToNext());
        }
        return EmployeeAddress;
    }

    @Override
    public EmployeeAddress update(EmployeeAddress entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_POSTALCODE, entity.getPostalCode());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())}
        );
        return entity;
    }

    @Override
    public EmployeeAddress delete(EmployeeAddress entity) {
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
