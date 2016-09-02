
package com.tp2assignment6.domaindesign.domain.repository.booking.Impl;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp2assignment6.domaindesign.domain.conf.databases.DBConstants;
import com.tp2assignment6.domaindesign.domain.domain.booking.Site;
import com.tp2assignment6.domaindesign.domain.repository.booking.SiteRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/21.
 */
public class SiteRepositoryImpl extends SQLiteOpenHelper implements SiteRepository {
    public static final String TABLE_NAME = "site";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "idNumber";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_RESERVATIONURL = "reservationUrl";

    //Database creation!
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_URL + " TEXT NOT NULL, "
            + COLUMN_RESERVATIONURL + " TEXT NOT NULL );";

    public  SiteRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException{db = this.getWritableDatabase();}

    public void close(){this.close();}

    @Override
    public Site create(Site entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_URL, entity.getUrl());
        values.put(COLUMN_RESERVATIONURL, entity.getReservationUrl());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Site insertEntity = new Site.Builder()
                .copy(entity)
                .idNumber(new Long(id))
                .build();
        return insertEntity;
    }

    @Override
    public Site readById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_URL,
                        COLUMN_RESERVATIONURL},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()){
            final Site Site = new Site.Builder()
                    .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .url(cursor.getString(cursor.getColumnIndex(COLUMN_URL)))
                    .reservationUrl(cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATIONURL)))
                    .build();
            return Site;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<Site> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Site> Site = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                final Site setting = new Site.Builder()
                        .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .url(cursor.getString(cursor.getColumnIndex(COLUMN_URL)))
                        .reservationUrl(cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATIONURL)))
                        .build();
                Site.add(setting);
            }while (cursor.moveToNext());
        }
        return Site;
    }

    @Override
    public Site update(Site entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_URL, entity.getUrl());
        values.put(COLUMN_RESERVATIONURL, entity.getReservationUrl());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())}
        );
        return entity;
    }

    @Override
    public Site delete(Site entity) {
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
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
