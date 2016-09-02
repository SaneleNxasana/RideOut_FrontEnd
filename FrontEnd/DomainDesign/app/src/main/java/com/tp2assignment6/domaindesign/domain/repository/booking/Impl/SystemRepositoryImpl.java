package com.tp2assignment6.domaindesign.domain.repository.booking.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp2assignment6.domaindesign.domain.conf.databases.DBConstants;
import com.tp2assignment6.domaindesign.domain.domain.booking.System;
import com.tp2assignment6.domaindesign.domain.repository.booking.SystemRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/21.
 */
public class SystemRepositoryImpl extends SQLiteOpenHelper implements SystemRepository {
    public static final String TABLE_NAME = "system";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "idNumber";
    public static final String COLUMN_USERDETAILS = "userDetails";
    public static final String COLUMN_RESERVATIONDETAILS = "reservationDetails";

    //Database creation!
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERDETAILS + " TEXT NOT NULL, "
            + COLUMN_RESERVATIONDETAILS + " TEXT NOT NULL );";

    public SystemRepositoryImpl (Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException{db = this.getWritableDatabase();}

    public void close(){this.close();}

    @Override
    public System create(System entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_USERDETAILS, entity.getUserDetails());
        values.put(COLUMN_RESERVATIONDETAILS, entity.getReservationDetails());
        long id  = db.insertOrThrow(TABLE_NAME, null, values);
        System insertEntity = new System.Builder()
                .copy(entity)
                .idNumber(new Long(id))
                .build();
        return insertEntity;
    }

    @Override
    public System readById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_USERDETAILS,
                        COLUMN_RESERVATIONDETAILS},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()){
            final System System = new System.Builder()
                    .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .userDetails(cursor.getString(cursor.getColumnIndex(COLUMN_USERDETAILS)))
                    .reservationDetails(cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATIONDETAILS)))
                    .build();
            return System;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<System> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<System> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final System setting = new System.Builder()
                        .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .userDetails(cursor.getString(cursor.getColumnIndex(COLUMN_USERDETAILS)))
                        .reservationDetails(cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATIONDETAILS)))
                        .build();
                Person.add(setting);
            } while (cursor.moveToNext());
        }
        return Person;
    }

    @Override
    public System update(System entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_USERDETAILS, entity.getUserDetails());
        values.put(COLUMN_RESERVATIONDETAILS, entity.getReservationDetails());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())}
        );
        return entity;
    }

    @Override
    public System delete(System entity) {
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
