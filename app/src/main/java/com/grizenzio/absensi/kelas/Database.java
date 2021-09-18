package com.grizenzio.absensi.kelas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    //Nama Database Local
    private static final String DATABASE_NAME = "absen.db";

    //Nama Tabel Local
    private static final String TABLE_NAME = "absensi";

    //Nama Kolom Local
    private static final String ID = "id";
    private static final String NIDN = "nidn";
    private static final String NAMA = "nama";
    private static final String TANGGAL = "tanggal";
    private static final String JAM = "jam";
    private static final String STATUS = "status";
    private static final String TELAT = "telat";

    public Database(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID + " TEXT PRIMARY KEY," + NIDN + " TEXT," + NAMA + " TEXT," + TANGGAL + " TEXT," + JAM + " TEXT," + STATUS + " TEXT," + TELAT + " TEXT " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Prosedure Tambah Data
    public boolean TambahData(String id, String nidn, String nama, String tanggal, String jam, String status, String telat) {

        //cek data nik jika ada di database
        if (CekDataAbsen(nidn, tanggal)) {

            //mengirim notif bahwa data itu sudah ada
            return false;

        } else {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, id);
            contentValues.put(NIDN, nidn);
            contentValues.put(NAMA, nama);
            contentValues.put(TANGGAL, tanggal);
            contentValues.put(JAM, jam);
            contentValues.put(STATUS, status);
            contentValues.put(TELAT, telat);
            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;
        }
    }

    public Cursor TampilData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    //Prosedure Tampil Data RecyclerView
    public Cursor TampilDataRv() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {ID, NIDN, NAMA, TANGGAL, JAM, STATUS};
        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

    //Prosedure Cek Data
    public Boolean CekDataAbsen(String nidn, String tanggal) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT nidn FROM absensi WHERE nidn = '" + nidn + "' AND tanggal = '" + tanggal + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    //Prosedure Ubah Data
    public boolean UbahData(String id, String nidn, String nama, String tanggal, String jam, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NIDN, nidn);
        contentValues.put(NAMA, nama);
        contentValues.put(TANGGAL, tanggal);
        contentValues.put(JAM, jam);
        contentValues.put(STATUS, status);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{id});
        return true;
    }

    //Prosedure Hapus Data
    public Integer HapusData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[]{id});
    }
}
