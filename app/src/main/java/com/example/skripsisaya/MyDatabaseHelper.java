package com.example.skripsisaya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context ctx;
    private SQLiteDatabase db;
    private static final String DATABASE_NAME ="db_laundry_asian";
    private static final int DATABASE_VERSION =1;
    private static final String  TABLE_PELANGGAN = "tbl_pelanggan";
    private static final String TABLE_LAYANAN = "tbl_layanan";

    private static final String TABLE_PENGELUARAN = "tbl_pengeluaran";

    private static final String TABLE_TRANSAKSI = "tbl_transaksi";
    private static final String TABLE_PENGEMBALIAN = "tbl_pengembalian";


// Pelanggan table columns names
private static final String FIELD_ID = "id_pelanggan";
private static final String FIELD_NAMAPELANGGAN = "nama_pelanggan";
private static final String FIELD_TELEPONPELANGGAN ="telepon_pelanggan";
private static final String FIELD_ALAMATPELANGGAN = "alamat_pelanggan";

    // lAYANAN table columns names
    private static final String FIELD_ID_JASA = "id_jasa";
    private static final String FIELD_NAMA_LAYANAN = "nama_layanan";
    private static final String FIELD_SATUAN_JASA ="satuan_jasa";
    private static final String FIELD_HARGA_JASA = "HARGA_JASA";

    // kebutuhan table columns names
    private static final String FIELD_ID_PENGELUARAN = "id_pengeluaran";
    private static final String FIELD_NAMA_BARANG = "nama_barang";
    private static final String FIELD_JUMLAH ="jumlah_barang";
    private static final String FIELD_HARGA_PENGELUARAN = "harga_pengeluaran";
    private static final String FIELD_TANGGAL_PENGELUARAN = "tanggal";


    // transaksi

    private static final String FIELD_ID_TRANSAKSI = "id_transaksi";
    private static final String FIELD_NAMA_PELANGGAN_TRANSAKSI = "nama_pelanggan_transaksi";
    private static final String FIELD_TELEPON_PELANGGAN_TRANSAKSI ="whatapp_pelanggan_transaksi";
    private static final String FIELD_ALAMAT_PELANGGAN_TRANSAKSI = "alamat_pelanggan_transaksi";
    private static final String FIELD_NAMA_LAYANAN_TRANSAKSI = "layanan_transaksi";
    private static final String FIELD_HARGA_JASA_TRANSAKSI = "harga_transaksi";
    private static final String FIELD_BERAT_PAKAIAN_TRANSAKSI = "berat_pakaian";
    private static final String FIELD_BIAYA_TRANSAKSI = "biaya_transaksi";
    private static final String FIELD_BAYAR_TRANSAKSI = "bayar_transaksi";
    private static final String FIELD_KEMBALIAN_TRANSAKSI = "kembalian_transaksi";
    private static final String FIELD_TANGGAL_TRANSAKSI = "tanggal_transaksi";


    private static final String FIELD_ID_PENGEMBALIAN = "id_pengembalian";
    private static final String FIELD_ID_TRANSAKSI_PENGEMBALIAN = "id_transaksi_pengembalian";
    private static final String FIELD_NAMA_PELANGGANPENGEMBALIAN = "nama_pelanggan_pengembalian";
    private static final String FIELD_TELEPON_PELANGGANPENGEMBALIAN ="whatsapp_pelanggan_pengembalian";
    private static final String FIELD_BERAT= "berat";
    private static final String FIELD_NAMA_LAYANAN_PENGEMBALIAN = "layanan_pengembalian";
    private static final String FIELD_BIAYA = "biaya";
    private static final String FIELD_STATUS_PESAN= "status_pesan_laundry";
    private static final String FIELD_TANGGAL_PENGAMBILAN = "tanggal_pengambilan";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context , DATABASE_NAME,null,DATABASE_VERSION);
        this.ctx = context;
        db  = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String querypelanggan = "CREATE TABLE "+ TABLE_PELANGGAN + "( "+
            FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_NAMAPELANGGAN + " VARCHAR(30)," +
            FIELD_TELEPONPELANGGAN+ " VARCHAR(18)," +
            FIELD_ALAMATPELANGGAN+ " VARCHAR(50));"
            ;
    db.execSQL(querypelanggan);

        String queryLayanan = "CREATE TABLE "+ TABLE_LAYANAN + "( "+
                FIELD_ID_JASA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_NAMA_LAYANAN+ " VARCHAR(15)," +
                FIELD_SATUAN_JASA+ " VARCHAR(10)," +
                FIELD_HARGA_JASA+ " INTEGER(20));"
                ;
        db.execSQL(queryLayanan);

        String querypengeluaran = "CREATE TABLE "+ TABLE_PENGELUARAN + "( "+
                FIELD_ID_PENGELUARAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_NAMA_BARANG+ " VARCHAR(15)," +
                FIELD_JUMLAH+ " INTEGER(10)," +
                FIELD_HARGA_PENGELUARAN+ " INTEGER(20)," +
                FIELD_TANGGAL_PENGELUARAN+ " TEXT);"
                ;
        db.execSQL(querypengeluaran);


        String querytransaksi = "CREATE TABLE "+ TABLE_TRANSAKSI + "( "+
                FIELD_ID_TRANSAKSI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_NAMA_PELANGGAN_TRANSAKSI+ " VARCHAR(15)," +
                FIELD_HARGA_JASA_TRANSAKSI+ " INTEGER(10)," +
                FIELD_BERAT_PAKAIAN_TRANSAKSI+ " INTEGER(2)," +
                FIELD_NAMA_LAYANAN_TRANSAKSI+ "  VARCHAR(15)," +
                FIELD_BAYAR_TRANSAKSI+ " INTEGER(10)," +
                FIELD_TANGGAL_TRANSAKSI+ " timestamp default (datetime ('now', 'localtime'))," +
                FIELD_KEMBALIAN_TRANSAKSI+ " INTEGER(10)," +
                FIELD_BIAYA_TRANSAKSI+ " INTEGER(10)," +
                FIELD_TELEPON_PELANGGAN_TRANSAKSI+ " INTEGER(15)," +
                FIELD_ALAMAT_PELANGGAN_TRANSAKSI+ " VARCHAR(25));"
        ;
        db.execSQL(querytransaksi);


    String queryambil = "CREATE TABLE "+ TABLE_PENGEMBALIAN + "( "+
            FIELD_ID_PENGEMBALIAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_ID_TRANSAKSI_PENGEMBALIAN + " INTEGER(10) , " +
            FIELD_NAMA_PELANGGANPENGEMBALIAN + " VARCHAR(15)," +
            FIELD_TELEPON_PELANGGANPENGEMBALIAN+ " VARCHAR(15)," +
            FIELD_BERAT+ " INTEGER(10)," +
            FIELD_NAMA_LAYANAN_PENGEMBALIAN+ "  VARCHAR(15)," +
            FIELD_BIAYA+ " INTEGER(10)," +
            FIELD_STATUS_PESAN+ " VARCHAR(20)," +
            FIELD_TANGGAL_PENGAMBILAN+ " timestamp default (datetime ('now', 'localtime')));"
            ;
        db.execSQL(queryambil);
}


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PELANGGAN);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_LAYANAN);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PENGELUARAN);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TRANSAKSI);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PENGEMBALIAN);
        onCreate(db);
    }
    public long tambahpelanggan (String namapelanggan , String teleponpelanggan , String alamatpelanggan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_NAMAPELANGGAN, namapelanggan);
        cv.put(FIELD_TELEPONPELANGGAN, teleponpelanggan);
        cv.put(FIELD_ALAMATPELANGGAN, alamatpelanggan);

        long eksekusi = db.insert(TABLE_PELANGGAN, null, cv);
        return eksekusi;
    }

    public long tambahlayanan (String namalayanan , String satuanlayan , int hargalayanan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA_LAYANAN, namalayanan);
        cv.put(FIELD_SATUAN_JASA, satuanlayan);
        cv.put(FIELD_HARGA_JASA, hargalayanan);

        long eksekusi = db.insert(TABLE_LAYANAN, null, cv);
        return eksekusi;
    }

    public long tambahpengeluaran (String namabarang , int jumlahbarang , int hargabarang , String tanggalpengeluran) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA_BARANG, namabarang);
        cv.put(FIELD_JUMLAH, jumlahbarang);
        cv.put(FIELD_HARGA_PENGELUARAN, hargabarang);
        cv.put(FIELD_TANGGAL_PENGELUARAN, tanggalpengeluran);

        long eksekusi = db.insert(TABLE_PENGELUARAN, null, cv);
        return eksekusi;
    }
    public String getSumPengeluaran(){

        SQLiteDatabase db = this.getWritableDatabase();
        String Sjumlahmasuk = null;

        String query = "SELECT SUM (harga_pengeluaran) FROM " + TABLE_PENGELUARAN;
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        if (cursor.moveToFirst()){

        }else {
            Sjumlahmasuk = "0";
        }


        cursor.close();
        return Sjumlahmasuk;
    }

    public long tambahtransaksi (String NamapelangganTransaksi , String Whatsapppelanggan ,String AlamatpelangganTransaksi , String TanggalTransaksi , String NamalayananTransaksi ,int HargalayananTransaksi , int BeratPakaian , int BiayaTransaksi , int BayarTransaksi , int KembalianTransaksi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_NAMA_PELANGGAN_TRANSAKSI, NamapelangganTransaksi);
        cv.put(FIELD_TELEPON_PELANGGAN_TRANSAKSI, Whatsapppelanggan);
        cv.put(FIELD_ALAMAT_PELANGGAN_TRANSAKSI, AlamatpelangganTransaksi);
        cv.put(FIELD_TANGGAL_TRANSAKSI, TanggalTransaksi);
        cv.put(FIELD_NAMA_LAYANAN_TRANSAKSI, NamalayananTransaksi);
        cv.put(FIELD_HARGA_JASA_TRANSAKSI, HargalayananTransaksi);
        cv.put(FIELD_BERAT_PAKAIAN_TRANSAKSI, BeratPakaian);
        cv.put(FIELD_BIAYA_TRANSAKSI, BiayaTransaksi);
        cv.put(FIELD_BAYAR_TRANSAKSI, BayarTransaksi);
        cv.put(FIELD_KEMBALIAN_TRANSAKSI, KembalianTransaksi);
        long eksekusi = db.insert(TABLE_TRANSAKSI, null, cv);
        return eksekusi;
    }

    public long tambahpengambilan (int idTransaksipengembalian , String Namapelangganpengembalian,String Whatsapppelanggan,
                                   int BeratPakaian , String namalayananTransaksi , int BiayaPelanggan , String Statuspesan , String Tangganpengembalian) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put( FIELD_ID_TRANSAKSI_PENGEMBALIAN, idTransaksipengembalian);
        cv.put(FIELD_NAMA_PELANGGANPENGEMBALIAN, Namapelangganpengembalian);
        cv.put( FIELD_TELEPON_PELANGGANPENGEMBALIAN, Whatsapppelanggan);
        cv.put(FIELD_BERAT, BeratPakaian);
        cv.put(FIELD_NAMA_LAYANAN_PENGEMBALIAN, namalayananTransaksi);
        cv.put(FIELD_BIAYA, BiayaPelanggan);
        cv.put(FIELD_STATUS_PESAN,Statuspesan);
        cv.put(FIELD_TANGGAL_PENGAMBILAN, Tangganpengembalian);
        long eksekusi = db.insert(TABLE_PENGEMBALIAN, null, cv);
        return eksekusi;
    }

    public String getSum(){

        SQLiteDatabase db = this.getWritableDatabase();
        String Sjumlahmasuk = null;

        String query = "SELECT SUM (biaya) FROM " + TABLE_PENGEMBALIAN;
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        if (cursor.moveToFirst()){

        }else {
            Sjumlahmasuk = "0";
        }


        cursor.close();
        return Sjumlahmasuk;
    }


    public Cursor bacaDataPelanggan () {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PELANGGAN;

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;

    }
    public Cursor bacaDataPengeluaran () {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PENGELUARAN;

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;

    }

    public Cursor tampilLayanan () {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LAYANAN;

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;

    }
    public Cursor tampilTransaksi () {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TRANSAKSI;

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;

    }

    public Cursor tampilPengembalian () {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PENGEMBALIAN;

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;

    }

    public long hapusPelanggan(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        long eksekusi = db.delete(TABLE_PELANGGAN , "id_pelanggan = ?", new String[]{id});
        return eksekusi;
    }

    public long hapusLayanan(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        long eksekusi = db.delete(TABLE_LAYANAN , "id_jasa = ?", new String[]{id});
        return eksekusi;
    }

    public long hapuspengeluaran(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        long eksekusi = db.delete(TABLE_PENGELUARAN , "id_pengeluaran = ?", new String[]{id});
        return eksekusi;
    }

    public long hapustransaksi(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        long eksekusi = db.delete(TABLE_TRANSAKSI , "id_transaksi = ?", new String[]{id});
        return eksekusi;
    }


    public long ubahPelanggan (String id , String nama , String telepon , String alamat){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();

        cv.put(FIELD_NAMAPELANGGAN, nama);
        cv.put(FIELD_TELEPONPELANGGAN, telepon);
        cv.put(FIELD_ALAMATPELANGGAN, alamat);

        long eksekusi = db.update(TABLE_PELANGGAN, cv, "id_pelanggan = ?", new String[]{id});
        return eksekusi;
    }

    public long ubahLayanan (String idlayanan , String namalayanan , String satuanlayanan , String hargalayanan){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();

        cv.put(FIELD_NAMA_LAYANAN, namalayanan);
        cv.put(FIELD_SATUAN_JASA, satuanlayanan);
        cv.put(FIELD_HARGA_JASA, hargalayanan);

        long eksekusi = db.update(TABLE_LAYANAN, cv, "id_jasa = ?", new String[]{idlayanan});
        return eksekusi;
    }

    public long ubahpengeluaran (String idpengeluaran , String namabarang , String jumlah , String harga , String tanggal){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(FIELD_NAMA_BARANG, namabarang);
        cv.put(FIELD_JUMLAH, jumlah);
        cv.put(FIELD_HARGA_PENGELUARAN, harga);
        cv.put(FIELD_TANGGAL_PENGELUARAN, tanggal);

        long eksekusi = db.update(TABLE_PENGELUARAN, cv, "id_pengeluaran = ?", new String[]{idpengeluaran});
        return eksekusi;
    }

    public long ubahtransaksi (String idtransaksi , String NamapelangganTransaksi , int Whatsapppelanggan ,String AlamatpelangganTransaksi ,
                               String TanggalTransaksi , String NamalayananTransaksi ,int HargalayananTransaksi ,
                                 int BeratPakaian , int BiayaTransaksi , int BayarTransaksi , int KembalianTransaksi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA_PELANGGAN_TRANSAKSI, NamapelangganTransaksi);
        cv.put(FIELD_TELEPON_PELANGGAN_TRANSAKSI, Whatsapppelanggan);
        cv.put(FIELD_ALAMAT_PELANGGAN_TRANSAKSI, AlamatpelangganTransaksi);
        cv.put(FIELD_TANGGAL_TRANSAKSI, TanggalTransaksi);
        cv.put(FIELD_NAMA_LAYANAN_TRANSAKSI, NamalayananTransaksi);
        cv.put(FIELD_HARGA_JASA_TRANSAKSI, HargalayananTransaksi);
        cv.put(FIELD_BERAT_PAKAIAN_TRANSAKSI, BeratPakaian);
        cv.put(FIELD_BIAYA_TRANSAKSI, BiayaTransaksi);
        cv.put(FIELD_BAYAR_TRANSAKSI, BayarTransaksi);
        cv.put(FIELD_KEMBALIAN_TRANSAKSI, KembalianTransaksi);
        long eksekusi = db.update(TABLE_TRANSAKSI,  cv, "id_transaksi= ?", new String[]{idtransaksi});
        return eksekusi;
    }

    public List<Layanan> getAllLayanans(){
        List<Layanan> layananList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LAYANAN;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int idlayanan = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_ID_JASA));
                String namalayanan = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_NAMA_LAYANAN));
                String satuanjasa = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_SATUAN_JASA));
                int hargajasa = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_HARGA_JASA));

                layananList.add(new Layanan(idlayanan, namalayanan,satuanjasa,hargajasa));
            }while (cursor.moveToNext());
        }else{
            Log.d("Cursor", " tidak ada data");

        }
        cursor.close();
        return layananList;
    }

    public List<Pengeluaran> getAllPengeluarans(){
        List<Pengeluaran> pengeluaranList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PENGELUARAN;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int idpengeluaran = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_ID_PENGELUARAN));
                String namabarang = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_NAMA_BARANG));
                String jumlah = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_JUMLAH));
                int harga = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_HARGA_PENGELUARAN));
                String tanggal = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_TANGGAL_PENGELUARAN));
                pengeluaranList.add(new Pengeluaran(idpengeluaran, namabarang,jumlah,harga,tanggal));
            }while (cursor.moveToNext());
        }else{
            Log.d("Cursor", " tidak ada data");

        }
        cursor.close();
        return pengeluaranList;
    }
    public List<Pelanggan> getAllPelanggans(){
        List<Pelanggan> pelangganList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PELANGGAN;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int idpelanggan = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_ID));
                String namapelanggan = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_NAMAPELANGGAN));
                String teleponpelanggan = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_TELEPONPELANGGAN));
                String alamatpelanggan = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_ALAMATPELANGGAN));
                pelangganList.add(new Pelanggan(idpelanggan, namapelanggan,teleponpelanggan,alamatpelanggan));
            }while (cursor.moveToNext());
        }else{
            Log.d("Cursor", " tidak ada data");

        }
        cursor.close();
        return pelangganList;
    }

    public List<Transaksi> getAllTransaksi(){
        List<Transaksi> transaksiList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TRANSAKSI;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id_transaksi = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_ID_TRANSAKSI));
                String nama_pelanggan_transaksi = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_NAMA_PELANGGAN_TRANSAKSI));
                String whatapp_pelanggan_transaksi = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_TELEPON_PELANGGAN_TRANSAKSI));
                String alamat_pelanggan_transaksi = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_ALAMAT_PELANGGAN_TRANSAKSI));
                String layanan_transaksi = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_NAMA_LAYANAN_TRANSAKSI));
                int  harga_transaksi = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_HARGA_JASA_TRANSAKSI));
                int berat_pakaian = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_BERAT_PAKAIAN_TRANSAKSI));
                int biaya_transaksi = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_BIAYA_TRANSAKSI));
                int bayar_transaksi = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_BAYAR_TRANSAKSI));
                int kembalian_transaksi = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_KEMBALIAN_TRANSAKSI));
                String tanggal_transaksi = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_TANGGAL_TRANSAKSI));
                transaksiList.add(new Transaksi(id_transaksi, nama_pelanggan_transaksi,whatapp_pelanggan_transaksi,
                        alamat_pelanggan_transaksi,layanan_transaksi,harga_transaksi,berat_pakaian,biaya_transaksi,bayar_transaksi,kembalian_transaksi,tanggal_transaksi));
            }while (cursor.moveToNext());
        }else{
            Log.d("Cursor", " tidak ada data");

        }
        cursor.close();
        return transaksiList;
    }

    public List<Pengembalian> getAllPengembalian(){
        List<Pengembalian> pengembalianList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PENGEMBALIAN;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id_pengembalian = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper. FIELD_ID_PENGEMBALIAN));
                int id_transaksi = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_ID_TRANSAKSI_PENGEMBALIAN));
                String nama_pelanggan_transaksi = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_NAMA_PELANGGANPENGEMBALIAN));
                String whatapp_pelanggan_transaksi = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper. FIELD_TELEPON_PELANGGANPENGEMBALIAN));
                String layanan_transaksi = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_NAMA_LAYANAN_PENGEMBALIAN));
                int berat_pakaian = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_BERAT));
                int biaya_transaksi = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_BIAYA));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_STATUS_PESAN));
                String tanggal_transaksi = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.FIELD_TANGGAL_PENGAMBILAN));
                pengembalianList.add(new Pengembalian(id_pengembalian,id_transaksi, nama_pelanggan_transaksi ,whatapp_pelanggan_transaksi,
                        layanan_transaksi,berat_pakaian,biaya_transaksi,status,tanggal_transaksi));
            }while (cursor.moveToNext());
        }else{
            Log.d("Cursor", " tidak ada data");

        }
        cursor.close();
        return pengembalianList;
    }
}
