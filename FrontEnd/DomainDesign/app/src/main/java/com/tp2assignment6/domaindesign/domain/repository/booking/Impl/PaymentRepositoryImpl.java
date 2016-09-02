package com.tp2assignment6.domaindesign.domain.repository.booking.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp2assignment6.domaindesign.domain.conf.databases.DBConstants;
import com.tp2assignment6.domaindesign.domain.domain.booking.Payment;
import com.tp2assignment6.domaindesign.domain.repository.booking.PaymentRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/21.
 */
public class PaymentRepositoryImpl extends SQLiteOpenHelper implements PaymentRepository {
    public static final String TABLE_NAME = "payment";
    private SQLiteDatabase db;

    private static final String COLUMN_ID = "idNumber";
    private static final String COLUMN_PAYMENTTYPE = "paymentType";
    private static final String COLUMN_AMOUNT = "amount";

    //Database creation!
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PAYMENTTYPE + " TEXT NOT NULL, "
            + COLUMN_AMOUNT + " TEXT NOT NULL )";

    public PaymentRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException{
        db = this.getWritableDatabase();
    }

    public void close(){this.close();}

    @Override
    public Payment create(Payment entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_PAYMENTTYPE, entity.getPaymentType());
        values.put(COLUMN_AMOUNT, entity.getAmount());

        long idNumber = db.insertOrThrow(TABLE_NAME, null, values);
        Payment insertedEntity = new Payment.Builder()
                .copy(entity)
                .idNumber(new Long(idNumber))
                .build();
        return insertedEntity;
    }

    @Override
    public Payment readById(Long idNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_PAYMENTTYPE,
                        COLUMN_AMOUNT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(idNumber)},
                null, null, null);

        if(cursor.moveToFirst()){
            final Payment Payment = new Payment.Builder()
                    .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .paymentType(cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENTTYPE)))
                    .amount(cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)))
                    .build();
            return Payment;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<Payment> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Payment> Payment = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                final Payment setting = new Payment.Builder()
                        .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .paymentType(cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENTTYPE)))
                        .amount(cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)))
                        .build();
                Payment.add(setting);
            }while (cursor.moveToNext());
        }
        return Payment;
    }

    @Override
    public Payment update(Payment entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_PAYMENTTYPE, entity.getPaymentType());
        values.put(COLUMN_AMOUNT, entity.getAmount());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())}
        );
        return entity;
    }

    @Override
    public Payment delete(Payment entity) {
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
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
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
