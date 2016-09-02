package com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp2assignment6.domaindesign.domain.conf.databases.DBConstants;
import com.tp2assignment6.domaindesign.domain.domain.vehicle.Vehicle;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.VehicleRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/22.
 */
public class VehicleRepositoryImpl extends SQLiteOpenHelper implements VehicleRepository {
    public static final String TABLE_NAME = "vehicle";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "idNumber";
    public static final String COLUMN_VEHICLENAME = "vehicleName";
    public static final String COLUMN_VEHICLEMODEL = "vehicleModel";
    public static final String COLUMN_VEHICLEYEAR = "vehicleYear";

    //Database creation!
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_VEHICLENAME + " TEXT NOT NULL, "
            + COLUMN_VEHICLEMODEL + " TEXT NOT NULL, "
            + COLUMN_VEHICLEYEAR + " TEXT NOT NULL );";

    public VehicleRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {db = this.getWritableDatabase();}

    public void close() {this.close();}

    @Override
    public Vehicle create(Vehicle entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_VEHICLENAME, entity.getVehicleName());
        values.put(COLUMN_VEHICLEMODEL, entity.getVehicleModel());
        values.put(COLUMN_VEHICLEYEAR, entity.getVehicleYear());
        long idNumber = db.insertOrThrow(TABLE_NAME, null, values);
        Vehicle insertEntity = new Vehicle.Builder()
                .copy(entity)
                .idNumber(new Long(idNumber))
                .build();
        return insertEntity;
    }

    @Override
    public Vehicle readById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_VEHICLENAME,
                        COLUMN_VEHICLEMODEL,
                        COLUMN_VEHICLEYEAR,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor.moveToFirst()){
            final Vehicle Vehicle = new Vehicle.Builder()
                    .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .vehicleName(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLENAME)))
                    .vehicleModel(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLEMODEL)))
                    .vehicleYear(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLEYEAR)))
                    .build();
            return Vehicle;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<Vehicle> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Vehicle> Vehicle = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Vehicle setting = new Vehicle.Builder()
                        .idNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .vehicleName(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLENAME)))
                        .vehicleModel(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLEMODEL)))
                        .vehicleYear(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLEYEAR)))
                        .build();
                Vehicle.add(setting);
            } while (cursor.moveToNext());
        }
        return Vehicle;
    }

    @Override
    public Vehicle update(Vehicle entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNumber());
        values.put(COLUMN_VEHICLENAME, entity.getVehicleName());
        values.put(COLUMN_VEHICLEMODEL, entity.getVehicleModel());
        values.put(COLUMN_VEHICLEYEAR, entity.getVehicleYear());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNumber())}
        );
        return entity;
    }

    @Override
    public Vehicle delete(Vehicle entity) {
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
