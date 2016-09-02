package com.tp2assignment6.domaindesign.domain.repository.booking.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp2assignment6.domaindesign.domain.conf.databases.DBConstants;
import com.tp2assignment6.domaindesign.domain.domain.booking.Reservation;
import com.tp2assignment6.domaindesign.domain.repository.booking.ReservationRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/21.
 */
public class ReservationRepositoryImpl extends SQLiteOpenHelper implements ReservationRepository {
    public static final String TABLE_NAME = "reservation";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "idNumber";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_DURATION = "duration";

    //Database creation!
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DATE + " TEXT NOT NULL, "
            + COLUMN_DURATION + " TEXT NOT NULL );";

    public ReservationRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException{db = this.getWritableDatabase();}

    public void close(){this.close();}

    @Override
    public Reservation create(Reservation entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_DATE, entity.getDate());
        values.put(COLUMN_DURATION, entity.getDuration());
        long idNumber = db.insertOrThrow(TABLE_NAME, null, values);
        Reservation insertEntity = new Reservation.Builder()
                .copy(entity)
                .idNumber(new Long(idNumber))
                .build();
        return insertEntity;
    }

    @Override
    public Reservation readById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_DATE,
                        COLUMN_DURATION},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()){
            final Reservation Reservation = new Reservation.Builder()
                    .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                    .duration(cursor.getString(cursor.getColumnIndex(COLUMN_DURATION)))
                    .build();
            return Reservation;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<Reservation> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Reservation> Reservation = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                final Reservation setting = new Reservation.Builder()
                        .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .duration(cursor.getString(cursor.getColumnIndex(COLUMN_DURATION)))
                        .build();
                Reservation.add(setting);
            }while (cursor.moveToNext());
        }
        return Reservation;
    }

    @Override
    public Reservation update(Reservation entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_DATE, entity.getDate());
        values.put(COLUMN_DURATION, entity.getDuration());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())}
        );
        return  entity;
    }

    @Override
    public Reservation delete(Reservation entity) {
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
