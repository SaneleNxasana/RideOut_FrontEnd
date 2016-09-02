package com.tp2assignment6.domaindesign.domain.repository.user.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp2assignment6.domaindesign.domain.conf.databases.DBConstants;
import com.tp2assignment6.domaindesign.domain.domain.user.UserContact;
import com.tp2assignment6.domaindesign.domain.repository.user.UserContactRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/21.
 */
public class UserContactRepositoryImpl extends SQLiteOpenHelper implements UserContactRepository {
    public static final String TABLE_NAME = "userContact";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "idNumber";
    public static final String COLUMN_CONTACTTYPE = "contactType";
    public static final String COLUMN_CONTSCTVALUE = "contactValue";

    //Database creation!
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CONTACTTYPE + " TEXT NOT NULL, "
            + COLUMN_CONTSCTVALUE + " TEXT UNIQUE NOT NULL, );";

    public UserContactRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException{db = this.getWritableDatabase();}

    public void close(){this.close();}

    @Override
    public UserContact create(UserContact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_CONTACTTYPE, entity.getContactType());
        values.put(COLUMN_CONTSCTVALUE, entity.getContactValue());
        long idNumber = db.insertOrThrow(TABLE_NAME, null, values);
        UserContact insertEntity = new UserContact.Builder()
                .copy(entity)
                .idNumber(new Long(idNumber))
                .build();
        return insertEntity;
    }

    @Override
    public UserContact readById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CONTACTTYPE,
                        COLUMN_CONTSCTVALUE,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()){
            final UserContact UserContact = new UserContact.Builder()
                    .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .contactType(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTTYPE)))
                    .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTSCTVALUE)))
                    .build();
            return UserContact;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<UserContact> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<UserContact> UserContact = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                final UserContact setting = new UserContact.Builder()
                        .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .contactType(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTTYPE)))
                        .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTSCTVALUE)))
                        .build();
                UserContact.add(setting);
            }while (cursor.moveToNext());
        }
        return UserContact;
    }

    @Override
    public UserContact update(UserContact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_CONTACTTYPE, entity.getContactType());
        values.put(COLUMN_CONTSCTVALUE, entity.getContactValue());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())}
        );
        return entity;
    }

    @Override
    public UserContact delete(UserContact entity) {
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
