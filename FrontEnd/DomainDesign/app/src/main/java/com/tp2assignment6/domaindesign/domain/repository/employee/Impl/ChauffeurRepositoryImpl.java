package com.tp2assignment6.domaindesign.domain.repository.employee.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp2assignment6.domaindesign.domain.conf.databases.DBConstants;
import com.tp2assignment6.domaindesign.domain.domain.employee.Chauffeur;
import com.tp2assignment6.domaindesign.domain.repository.employee.ChauffeurRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class ChauffeurRepositoryImpl extends SQLiteOpenHelper implements ChauffeurRepository{
    public static final String TABLE_NAME = "chauffeur";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "idNumber";
    public static final String COLUMN_LICENCENUMBER = "licenceNumber";

    //Database creation!
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LICENCENUMBER + "TEXT UNIQUE NOT NULL );";

    public ChauffeurRepositoryImpl (Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException{db = this.getWritableDatabase();}

    public void close(){this.close();}

    @Override
    public Chauffeur create(Chauffeur entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_LICENCENUMBER, entity.getLicenceNumber());
        long idNumber = db.insertOrThrow(TABLE_NAME, null, values);
        Chauffeur insertEntity = new Chauffeur.Builder()
                .copy(entity)
                .idNumber(new Long(idNumber))
                .build();
        return insertEntity;
    }

    @Override
    public Chauffeur readById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_LICENCENUMBER},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()){
            final Chauffeur Chauffeur = new Chauffeur.Builder()
                    .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .licenceNumber(cursor.getString(cursor.getColumnIndex(COLUMN_LICENCENUMBER)))
                    .build();
            return Chauffeur;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<Chauffeur> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Chauffeur> Chauffeur = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                final Chauffeur setting = new Chauffeur.Builder()
                        .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .licenceNumber(cursor.getString(cursor.getColumnIndex(COLUMN_LICENCENUMBER)))
                        .build();
                Chauffeur.add(setting);
            }while (cursor.moveToNext());
        }
        return Chauffeur;
    }

    @Override
    public Chauffeur update(Chauffeur entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_LICENCENUMBER, entity.getLicenceNumber());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())}
        );
        return entity;
    }

    @Override
    public Chauffeur delete(Chauffeur entity) {
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
