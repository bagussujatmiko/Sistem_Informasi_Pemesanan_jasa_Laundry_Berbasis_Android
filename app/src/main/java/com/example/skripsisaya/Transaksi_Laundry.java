package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Transaksi_Laundry extends AppCompatActivity {


    private MyDatabaseHelper myDB;

    private AdapterListJasa adapterListJasaLayanan;

    private String id , nama , satuan , harga;
    private EditText etnamapelanggan , etnowhatsapp ,etalamat,ettanggal, etnmlayanan ,ethargalayanan ,etberat , etbiaya , etbayar ,etkembalian;
     private Button  btntambahtransaksi;

     private final int STORAGE_PERMISSION_CODE = 1;

     DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_laundry);

        myDB = new MyDatabaseHelper(Transaksi_Laundry.this);

        etnamapelanggan = findViewById(R.id.et_nama_Pelanggan);
        etnowhatsapp = findViewById(R.id.et_nowhatsapp);
        etnmlayanan = findViewById(R.id.et_nmlayanan);
        ethargalayanan = findViewById(R.id.et_harga_layanan);
        etberat = findViewById(R.id.et_berat);
        etbiaya = findViewById(R.id.et_biaya);
        etbayar = findViewById(R.id.et_bayar);
        etalamat = findViewById(R.id.et_nmAlamatPelanggan);
        etkembalian = findViewById(R.id.et_kembalian);
        ettanggal = findViewById(R.id.et_tanggalpesan);
        btntambahtransaksi = findViewById(R.id.btn_tambahtransaksi);


        // get data
        Intent intent = getIntent();
        id = intent.getStringExtra("vId");
        nama = intent.getStringExtra("vNamalayanan");
        satuan = intent.getStringExtra("vSatuanharga");
        harga = intent.getStringExtra("vHargajasa");

        // tempel data
        etnmlayanan.setText(nama);
        ethargalayanan.setText(harga);

        // tanggal
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        ettanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Transaksi_Laundry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day ) {

                        Calendar pilih = Calendar.getInstance();
                        pilih.set(year,month,day);
                        SimpleDateFormat formathari = new SimpleDateFormat("EEEE", Locale.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        String pilihhari = formathari.format(pilih.getTime());
                        String pilihtanggal = format.format(pilih.getTime());

                        String date = pilihhari + " ," + pilihtanggal;
                        ettanggal.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });



        btntambahtransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              SimpanDataTransaksi();

                String WhatsappPelanggan = etnowhatsapp.getText().toString();
                kirimpdfWa(WhatsappPelanggan);

            }
        });

        etberat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hitung();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etbayar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hitungharga();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

        }else {
            requestStoragePermissions();
        }

    }




    private void requestStoragePermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission dibutuhkan")
                    .setMessage("Permission Dibutuhkan Untuk Mendownload File ")
                    .setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Transaksi_Laundry.this , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE );
                        }
                    })
                    .setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }else{
            ActivityCompat.requestPermissions(Transaksi_Laundry.this , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE );
        }
    }

    private void hitungharga(){

        try {
            String Hargabayar = etbayar.getText().toString().trim();
            String hitungharga = etbiaya.getText().toString().trim();
            int harga = Integer.parseInt(hitungharga);
            int bayar = Integer.parseInt(Hargabayar);
            int biaya = bayar-harga;
            etkembalian.setText(String.valueOf(biaya));
        }catch (Exception e){
            return ;
        }
    }
    private void hitung() {

        int hargajadi = Integer.valueOf( ethargalayanan.getText().toString().trim());
        String nilaiberat = etberat.getText().toString().trim();
        int berat = Integer.parseInt(nilaiberat);
        int hitungharga = hargajadi * berat;
        etbiaya.setText(String.valueOf(hitungharga));

    }

    private void SimpanDataTransaksi () {
        String NamaPelangganTransaksi = etnamapelanggan.getText().toString();
        String WhatsappPelanggan = etnowhatsapp.getText().toString();
        String AlamatPelangganTransaksi = etalamat.getText().toString();
        String TanggalTransaksi = ettanggal.getText().toString().trim();
        String NamaLayananTransaksi = etnmlayanan.getText().toString().trim();
        String HargalayananTransaksi = ethargalayanan.getText().toString().trim();
        String BeratPakaian = etberat.getText().toString().trim();
        String BiayaTransaksi = etbiaya.getText().toString().trim();
        String BayarTransaksi = etbayar.getText().toString().trim();
        String KembalianTransaksi = etkembalian.getText().toString().trim();

        if (NamaPelangganTransaksi.trim().equals("")) {
            etnamapelanggan.setError("tidak boleh kosong");
        } else if (WhatsappPelanggan.trim().equals("")) {
            etnowhatsapp.setError("tidak boleh kosong");
        } else if (AlamatPelangganTransaksi.trim().equals("")) {
            etalamat.setError(" Tidak boleh kosong");
        } else if (TanggalTransaksi.trim().equals("")) {
            ettanggal.setError("Tidak boleh kosong ");
        } else if (NamaLayananTransaksi.trim().equals("")) {
            etnmlayanan.setError("tidak boleh kosong");
        } else if (HargalayananTransaksi.trim().equals("")) {
            ethargalayanan.setError(" Tidak boleh kosong");
        } else if (BeratPakaian.trim().equals("")) {
            etberat.setError("Tidak boleh kosong ");
        } else if (BiayaTransaksi.trim().equals("")) {
            etbiaya.setError("tidak boleh kosong");
        } else if (BayarTransaksi.trim().equals("")) {
            etbayar.setError(" Tidak boleh kosong");
        } else if (KembalianTransaksi.trim().equals("")) {
            etkembalian.setError("Tidak boleh kosong ");
        } else {

            MyDatabaseHelper mydb = new MyDatabaseHelper(Transaksi_Laundry.this);
            long eksekusi = mydb.tambahtransaksi(NamaPelangganTransaksi, WhatsappPelanggan, AlamatPelangganTransaksi, TanggalTransaksi, NamaLayananTransaksi, Integer.parseInt(HargalayananTransaksi),
                                                  Integer.parseInt(BeratPakaian), Integer.parseInt(BiayaTransaksi), Integer.parseInt(BayarTransaksi), Integer.parseInt(KembalianTransaksi));
            long jalan = mydb.tambahpelanggan(NamaPelangganTransaksi, WhatsappPelanggan, AlamatPelangganTransaksi);
            try {
                buatpdf();
            }catch (FileNotFoundException | MalformedURLException e){
                e.printStackTrace();
            }

            if (eksekusi == -1 || jalan == -1 ) {
                Toast.makeText(Transaksi_Laundry.this, "GAGAL TAMBAH DATA TRANSAKSI", Toast.LENGTH_SHORT).show();
                etnamapelanggan.requestFocus();

            } else {
                Toast.makeText(Transaksi_Laundry.this, "BERHASIL TAMBAH DATA TRANSAKSI", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    // cetak pdf
    private void buatpdf() throws FileNotFoundException, MalformedURLException {
        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfpath, "NotaDigital.pdf");
        OutputStream outputStream = new FileOutputStream(file);


        // pdf
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A5);

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

        // getData

        String Nama = etnamapelanggan.getText().toString();
        String wa = etnowhatsapp.getText().toString();
        String alamat = etalamat.getText().toString();
        String tanggal = ettanggal.getText().toString();
        String layanan = etnmlayanan.getText().toString();
        String harga = ethargalayanan.getText().toString();
        String berat = etberat.getText().toString();
        String total = etbiaya.getText().toString();
        String bayar = etbayar.getText().toString();
        String kembalian = etkembalian.getText().toString();

        // kolom pelanggan
        float col =250f;
        float lebarkolom [] = {col,col};
        Table tableinfo = new Table(lebarkolom);
        tableinfo.addCell(new Cell().add(new Paragraph("Nama         : " +Nama).setFontSize(12).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));
        tableinfo.addCell(new Cell().add(new Paragraph("Tanggal  : "+tanggal).setFontSize(12).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableinfo.addCell(new Cell().add(new Paragraph("No Whatsapp  : "+wa).setFontSize(12).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));
        tableinfo.addCell(new Cell().add(new Paragraph(" ").setFontSize(12).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableinfo.addCell(new Cell().add(new Paragraph("Alamat    : "+alamat).setFontSize(12).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));
        document.add(tableinfo);

        // paragraf baru
        Paragraph spasi1 = new Paragraph("").setFontSize(12);
        document.add(spasi1);

        float LebarKolomdua [] = {300f, col,240f , col};
        Table tablenota = new Table(LebarKolomdua);

        // judul kolom
        tablenota.addCell(new Cell().add(new Paragraph("Nama Layanan ").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
        tablenota.addCell(new Cell().add(new Paragraph("Harga Layanan ").setFontSize(12).setTextAlignment(TextAlignment.CENTER)).setBold());
        tablenota.addCell(new Cell().add(new Paragraph("Berat ").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
        tablenota.addCell(new Cell().add(new Paragraph("total  ").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));

        // isi kolom
        tablenota.addCell(new Cell().add(new Paragraph(layanan).setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
        tablenota.addCell(new Cell().add(new Paragraph( harga).setFontSize(12).setTextAlignment(TextAlignment.CENTER)).setBold());
        tablenota.addCell(new Cell().add(new Paragraph(berat).setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
        tablenota.addCell(new Cell().add(new Paragraph("RP.  "+String.valueOf(total)).setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
        tablenota.addCell(new Cell(0,3).add(new Paragraph("bayar ").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
        tablenota.addCell(new Cell().add(new Paragraph("RP.  "+String.valueOf(bayar)).setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
        tablenota.addCell(new Cell(0, 3).add(new Paragraph("Kembalian").setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
        tablenota.addCell(new Cell().add(new Paragraph("RP. "+String.valueOf(kembalian)).setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()));
        document.add(tablenota);
        document.close();
        Toast.makeText(this, "Nota berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void kirimpdfWa(String WhatsappPelanggan){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"NotaDigital.pdf");
        Uri pdfuri = FileProvider.getUriForFile(this,"com.example.skripsisaya.fileprovider", file);

        Intent kirim = new Intent(Intent.ACTION_SEND);
        kirim.setType("application/pdf");
        kirim.putExtra(Intent.EXTRA_STREAM, pdfuri);
        kirim.setPackage("com.whatsapp");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("62");
        stringBuilder.append(WhatsappPelanggan);
        stringBuilder.append("@s.whatsapp.net");
        kirim.putExtra("jid", stringBuilder.toString());
        kirim.setPackage("com.whatsapp");


        //kirim.putExtra("jid",WhatsappPelanggan + "@s.whatsapp.net");
      //  Uri uri = Uri.parse("https://api.whatsapp.com/send?phone ="+WhatsappPelanggan);
        kirim.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
           startActivity(kirim);

    }

}