package com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp2assignment6.domaindesign.domain.conf.databases.DBConstants;
import com.tp2assignment6.domaindesign.domain.domain.vehicle.Sedan;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.SedanRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class SedanRepositoryImpl extends SQLiteOpenHelper implements SedanRepository {
    public static final String TABLE_NAME = "suv";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "idNumber";
    public static final String COLUMN_REGISTRATIONNUMBER = "registrationNumber";
    public static final String COLUMN_NUMBERSEATS = "numberOfSeats";

    //Database creation!
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_REGISTRATIONNUMBER + " TEXT UNIQUE NOT NULL, "
            + COLUMN_NUMBERSEATS + " TEXT NOT NULL );";

    public SedanRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {db = this.getWritableDatabase();}

    public void close() {this.close();}

    @Override
    public Sedan create(Sedan entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_REGISTRATIONNUMBER, entity.getRegistrationNumber());
        values.put(COLUMN_NUMBERSEATS, entity.getNumberSeats());
        long idNumber = db.insertOrThrow(TABLE_NAME, null, values);
        Sedan insertEntity = new Sedan.Builder()
                .copy(entity)
                .idNumber(new Long(idNumber))
                .build();
        return insertEntity;
    }

    @Override
    public Sedan readById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_REGISTRATIONNUMBER,
                        COLUMN_NUMBERSEATS,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor.moveToFirst()){
            final Sedan Sedan = new Sedan.Builder()
                    .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .registrationNumber(cursor.getString(cursor.getColumnIndex(COLUMN_REGISTRATIONNUMBER)))
                    .numberSeats(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBERSEATS)))
                    .build();
            return Sedan;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<Sedan> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Sedan> Sedan = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Sedan setting = new Sedan.Builder()
                        .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .registrationNumber(cursor.getString(cursor.getColumnIndex(COLUMN_REGISTRATIONNUMBER)))
                        .numberSeats(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBERSEATS)))
                        .build();
                Sedan.add(setting);
            } while (cursor.moveToNext());
        }
        return Sedan;
    }

    @Override
    public Sedan update(Sedan entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_REGISTRATIONNUMBER, entity.getRegistrationNumber());
        values.put(COLUMN_NUMBERSEATS, entity.getNumberSeats());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())}
        );
        return entity;
    }

    @Override
    public Sedan delete(Sedan entity) {
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
