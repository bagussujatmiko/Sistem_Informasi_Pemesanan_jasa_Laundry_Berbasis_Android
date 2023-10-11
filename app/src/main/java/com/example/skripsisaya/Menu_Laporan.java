package com.example.skripsisaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.Manifest;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Menu_Laporan extends AppCompatActivity {
    private MyDatabaseHelper myHelper;
SQLiteDatabase sqLiteDatabase;
    Button btnlaporanlayanan ,btnlaporanpengeluaran , btnlaporanpelanggan , btnlaporanpemasukan , btnpengembalian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_laporan);
        btnlaporanlayanan = findViewById(R.id.btn_laporanlayanan);
        btnlaporanpengeluaran = findViewById(R.id.btn_laporanpengeluaran);
        btnlaporanpelanggan = findViewById(R.id.btn_laporanpelanggan);
        btnlaporanpemasukan = findViewById(R.id.btn_laporanpemasukan);
        btnpengembalian = findViewById(R.id.btn_laporanpengembalian);
        myHelper = new MyDatabaseHelper(this);
        sqLiteDatabase =myHelper.getReadableDatabase();
        if (Build.VERSION.SDK_INT >= 28) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //you code..
                //read_file()
            } else {
                //request permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                  Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1);
            }
        } else {

            //permission is automatically granted on sdk<23 upon installation
            //Your code
            //read_file()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()){

    // If you don't have access, launch a new activity to show the user the system's dialog
    // to allow access to the external storage
            }else{
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        }


        btnlaporanlayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    List<Layanan>layanans = myHelper.getAllLayanans();
                    buatPDF(layanans);
            }
        });
        btnlaporanpengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Pengeluaran>pengeluarans = myHelper.getAllPengeluarans();
                buatPDFpengeluaran(pengeluarans);
            }
        });

        btnlaporanpelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Pelanggan>pelanggans = myHelper.getAllPelanggans();
                buatPDFPelanggan(pelanggans);
            }
        });
        btnlaporanpemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Transaksi>transaksis = myHelper.getAllTransaksi();
                buatPDFtransaksi(transaksis);
            }
        });

        btnpengembalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Pengembalian>pengembalians = myHelper.getAllPengembalian();
                buatPDFPengembalian(pengembalians);
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    //read_file()
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void buatPDF (List<Layanan>layanans) {
        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +
                File.separator + "datalayanan.pdf";
        try {
            PdfWriter writer = new PdfWriter(pdfpath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            // header isi pdf
            Drawable drawable = getDrawable(R.drawable.headerpdf);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte []bitmapData = stream.toByteArray();
            ImageData data = ImageDataFactory.create(bitmapData);
            Image image =new Image(data);
            document.add(image);
            // paragraf
            Paragraph spasi = new Paragraph("").setFontSize(12);
            document.add(spasi);
            Paragraph judul = new Paragraph(" Laporan Data Layanan").setFontSize(20).setTextAlignment(TextAlignment.CENTER);
            document.add(judul);
            float LebarKolomdua [] = {300f, 250f,240f , 250f};
            Table tablenota = new Table(LebarKolomdua);
            // judul kolom
            tablenota.addCell(new Cell().add(new Paragraph("ID Layanan ").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablenota.addCell(new Cell().add(new Paragraph("Nama Layanan ").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablenota.addCell(new Cell().add(new Paragraph("Satuan Layanan ").setFontSize(12).setTextAlignment(TextAlignment.CENTER)).setBold());
            tablenota.addCell(new Cell().add(new Paragraph("Harga  ").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
            for( Layanan layanan : layanans){
                tablenota.addCell(String.valueOf(layanan.getIdlayanan()));
                tablenota.addCell(layanan.getNamalayanan());
                tablenota.addCell(layanan.getSatuanjasa());
                tablenota.addCell(String.valueOf(layanan.getHargajasa()));
            }
            document.add(tablenota);
            Paragraph spasi1 = new Paragraph("").setFontSize(12);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
            String currentDate = dateFormat.format(new Date());
            Paragraph dateParagraph = new Paragraph("Jakarta ,"+ currentDate).setFontSize(12).setTextAlignment(TextAlignment.RIGHT);
            document.add(dateParagraph);
            Paragraph kasir = new Paragraph("Kasir").setFontSize(12).setTextAlignment(TextAlignment.RIGHT);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(kasir);
            document.close();
            Toast.makeText(this, "PDF DATA Layanan DIBUAT", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(this, "tidak bisa buat pdf", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }
    private void buatPDFpengeluaran (List<Pengeluaran>pengeluarans) {
        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +
                File.separator + "datapengeluaran.pdf";
        try {
            PdfWriter writer = new PdfWriter(pdfpath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            // header isi pdf
            Drawable drawable = getDrawable(R.drawable.headerpdf);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte []bitmapData = stream.toByteArray();
            ImageData data = ImageDataFactory.create(bitmapData);
            Image image =new Image(data);
            document.add(image);
            // paragraf
            Paragraph spasi = new Paragraph("").setFontSize(12);
            document.add(spasi);
            Paragraph judul = new Paragraph(" Laporan Data Pengeluaran").setFontSize(20).setTextAlignment(TextAlignment.CENTER);
            document.add(judul);
            float LebarKolomdua [] = {100f, 100f,100f , 100f,100f};
            Table tablepengeluaran = new Table(LebarKolomdua);
            // judul kolom
            tablepengeluaran.addCell(new Cell().add(new Paragraph("ID Pengeluaran ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Nama Barang ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Jumlah ").setFontSize(11).setTextAlignment(TextAlignment.CENTER)).setBold());
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Harga  ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Tanggal  ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));

            for( Pengeluaran pengeluaran : pengeluarans){
                tablepengeluaran.addCell(String.valueOf(pengeluaran.getIdpengeluaran()));
                tablepengeluaran.addCell(pengeluaran.getNamabarang());
                tablepengeluaran.addCell(pengeluaran.getJumlah());
                tablepengeluaran.addCell(String.valueOf(pengeluaran.getHarga()));
               tablepengeluaran.addCell(pengeluaran.getTanggal());
            }
            document.add(tablepengeluaran);
            Paragraph spasi1 = new Paragraph("").setFontSize(12);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);


            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
            String currentDate = dateFormat.format(new Date());
            Paragraph dateParagraph = new Paragraph("Jakarta ,"+ currentDate).setFontSize(12).setTextAlignment(TextAlignment.RIGHT);
            document.add(dateParagraph);
            Paragraph kasir = new Paragraph("Kasir").setFontSize(12).setTextAlignment(TextAlignment.RIGHT);


            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(kasir);
            document.close();
            Toast.makeText(this, "PDF Data Pengeluaran DIBUAT SUKSES", Toast.LENGTH_SHORT).show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void buatPDFPelanggan (List<Pelanggan>pelanggans) {
        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +
                File.separator + "datapelanggan.pdf";

        try {
            PdfWriter writer = new PdfWriter(pdfpath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);

            // header isi pdf
            Drawable drawable = getDrawable(R.drawable.headerpdf);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte []bitmapData = stream.toByteArray();

            ImageData data = ImageDataFactory.create(bitmapData);
            Image image =new Image(data);
            document.add(image);

            // paragraf
            Paragraph spasi = new Paragraph("").setFontSize(12);
            document.add(spasi);

            Paragraph judul = new Paragraph(" Laporan Data Pelanggan").setFontSize(20).setTextAlignment(TextAlignment.CENTER);
            document.add(judul);

            float LebarKolomdua [] = {300f, 250f,240f , 250f};
            Table tablenota = new Table(LebarKolomdua);

            // judul kolom
            tablenota.addCell(new Cell().add(new Paragraph("ID Pelanggan ").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablenota.addCell(new Cell().add(new Paragraph("Nama Pelanggan ").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablenota.addCell(new Cell().add(new Paragraph("Telepon Pelanggan ").setFontSize(12).setTextAlignment(TextAlignment.CENTER)).setBold());
            tablenota.addCell(new Cell().add(new Paragraph("Alamat  ").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
            for( Pelanggan pelanggan : pelanggans){
                tablenota.addCell(String.valueOf(pelanggan.getIdpelanggan()));
                tablenota.addCell(pelanggan.getNamapelanggan());
                tablenota.addCell(pelanggan.getTeleponpelanggan());
                tablenota.addCell(String.valueOf(pelanggan.getAlamatpelanggan()));
            }
            document.add(tablenota);
            Paragraph spasi1 = new Paragraph("").setFontSize(12);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);


            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
            String currentDate = dateFormat.format(new Date());
            Paragraph dateParagraph = new Paragraph("Jakarta ,"+ currentDate).setFontSize(12).setTextAlignment(TextAlignment.RIGHT);
            document.add(dateParagraph);
            Paragraph kasir = new Paragraph("Kasir").setFontSize(12).setTextAlignment(TextAlignment.RIGHT);


            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(kasir);
            document.close();
            Toast.makeText(this, "PDF DATA PELANGGAN DIBUAT", Toast.LENGTH_SHORT).show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    private void buatPDFtransaksi (List<Transaksi>transaksis) {
        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +
                File.separator + "datatransaksi.pdf";

        try {
            PdfWriter writer = new PdfWriter(pdfpath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);

            // header isi pdf
            Drawable drawable = getDrawable(R.drawable.headerpdf);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte []bitmapData = stream.toByteArray();

            ImageData data = ImageDataFactory.create(bitmapData);
            Image image =new Image(data);
            document.add(image);

            // paragraf
            Paragraph spasi = new Paragraph("").setFontSize(12);
            document.add(spasi);

            Paragraph judul = new Paragraph(" Laporan Data Transaksi").setFontSize(20).setTextAlignment(TextAlignment.CENTER);
            document.add(judul);
            document.add(spasi);

            float LebarKolomdua [] = {20f, 50f,50f , 50f,50f,50f, 50f,50f , 50f,50f,50};
            Table tablepengeluaran = new Table(LebarKolomdua);

            // judul kolom
            tablepengeluaran.addCell(new Cell().add(new Paragraph("ID Transaksi ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Tanggal Transaksi ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Nama Pelanggan ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("no Whatsapp ").setFontSize(11).setTextAlignment(TextAlignment.CENTER)).setBold());
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Alamat  ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Layanan  ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Harga ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Berat").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Biaya ").setFontSize(11).setTextAlignment(TextAlignment.CENTER)).setBold());
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Bayar ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Kembalian  ").setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBold()));

            for( Transaksi transaksi : transaksis){
                tablepengeluaran.addCell(String.valueOf(transaksi.getId_transaksi()));
                tablepengeluaran.addCell(transaksi.getTanggal_transaksi());
                tablepengeluaran.addCell(transaksi.getNama_pelanggan_transaksi());
                tablepengeluaran.addCell(transaksi.getWhatapp_pelanggan_transaksi());
                tablepengeluaran.addCell(transaksi.getAlamat_pelanggan_transaksi());
                tablepengeluaran.addCell(transaksi.getLayanan_transaksi());
                tablepengeluaran.addCell(String.valueOf(transaksi.getHarga_transaksi()));
                tablepengeluaran.addCell(String.valueOf(transaksi.getBerat_pakaian()));
                tablepengeluaran.addCell(String.valueOf(transaksi.getBiaya_transaksi()));
                tablepengeluaran.addCell(String.valueOf(transaksi.getBayar_transaksi()));
                tablepengeluaran.addCell(String.valueOf(transaksi.getKembalian_transaksi()));
            }
            document.add(tablepengeluaran);
            Paragraph spasi1 = new Paragraph("").setFontSize(12);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);


            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
            String currentDate = dateFormat.format(new Date());
            Paragraph dateParagraph = new Paragraph("Jakarta ,"+ currentDate).setFontSize(12).setTextAlignment(TextAlignment.RIGHT);
            document.add(dateParagraph);
            Paragraph kasir = new Paragraph("Kasir").setFontSize(12).setTextAlignment(TextAlignment.RIGHT);


            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(kasir);
            document.close();
            Toast.makeText(this, "PDF DATA TRANSAKSI DIBUAT SUKSES", Toast.LENGTH_SHORT).show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void buatPDFPengembalian (List<Pengembalian>pengembalians) {
        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +
                File.separator + "datapengembalian.pdf";

        try {
            PdfWriter writer = new PdfWriter(pdfpath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);

            // header isi pdf
            Drawable drawable = getDrawable(R.drawable.headerpdf);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte []bitmapData = stream.toByteArray();

            ImageData data = ImageDataFactory.create(bitmapData);
            Image image =new Image(data);
            document.add(image);

            // paragraf
            Paragraph spasi = new Paragraph("").setFontSize(12);
            document.add(spasi);
            Paragraph judul = new Paragraph(" Laporan Data Pengambilan").setFontSize(20).setTextAlignment(TextAlignment.CENTER);
            document.add(judul);

            float LebarKolomdua [] = {20f, 50f,50f , 50f,50f,50f, 50f,50f , 50f};
            Table tablepengeluaran = new Table(LebarKolomdua);

            // judul kolom
            tablepengeluaran.addCell(new Cell().add(new Paragraph("ID Pengembalian ").setFontSize(9).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("ID Transaksi ").setFontSize(9).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Nama Pelanggan ").setFontSize(9).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("no Whatsapp ").setFontSize(9).setTextAlignment(TextAlignment.CENTER)).setBold());
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Layanan  ").setFontSize(9).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Berat").setFontSize(9).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Biaya ").setFontSize(9).setTextAlignment(TextAlignment.CENTER)).setBold());
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Status ").setFontSize(9).setTextAlignment(TextAlignment.CENTER).setBold()));
            tablepengeluaran.addCell(new Cell().add(new Paragraph("Tanggal Pengambilan ").setFontSize(9).setTextAlignment(TextAlignment.CENTER).setBold()));

            for( Pengembalian pengembalian : pengembalians){
                tablepengeluaran.addCell(String.valueOf(pengembalian.getId_pengembalian()));
                tablepengeluaran.addCell(String.valueOf(pengembalian.getId_transaksi()));
                tablepengeluaran.addCell(pengembalian.getNama_pelanggan_transaksi());
                tablepengeluaran.addCell(pengembalian.getWhatapp_pelanggan_transaksi());
                tablepengeluaran.addCell(pengembalian.getLayanan_transaksi());
                tablepengeluaran.addCell(String.valueOf(pengembalian.getBiaya_transaksi()));
                tablepengeluaran.addCell(String.valueOf(pengembalian.getBerat_pakaian()));
                tablepengeluaran.addCell(pengembalian.getStatus());
                tablepengeluaran.addCell(pengembalian.getTanggal_kembali());
            }
            document.add(tablepengeluaran);
            Paragraph spasi1 = new Paragraph("").setFontSize(12);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);


            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
            String currentDate = dateFormat.format(new Date());
            Paragraph dateParagraph = new Paragraph("Jakarta ,"+ currentDate).setFontSize(12).setTextAlignment(TextAlignment.RIGHT);
            document.add(dateParagraph);
            Paragraph kasir = new Paragraph("Kasir").setFontSize(12).setTextAlignment(TextAlignment.RIGHT);


            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(spasi1);
            document.add(kasir);
            document.close();
            Toast.makeText(this, "PDF Data PENGEMBALIAN DIBUAT SUKSES", Toast.LENGTH_SHORT).show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}